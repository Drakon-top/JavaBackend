package ru.tinkoff.edu.java.scrapper.web.sheduler;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.tinkoff.app.ParsingUrlService;
import ru.tinkoff.app.enums.TypeClient;
import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataGitHub;
import ru.tinkoff.app.url.UrlDataStackOverflow;
import ru.tinkoff.edu.java.scrapper.dto.*;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.web.ClientManager;
import ru.tinkoff.edu.java.scrapper.web.client.BotClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private static final Logger log =
            LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    private final LinkService jdbcLinkService;

    private final BotClient botClient;
    private final ClientManager clientManager;


    @Scheduled(fixedDelayString = "#{schedulerIntervalMs}")
    public void update() {
        List<DataLinkWithInformation> dataLinkWithInformation = jdbcLinkService.listLongTimeUpdate();
        for (DataLinkWithInformation data : dataLinkWithInformation) {
            UrlData urlData = ParsingUrlService.getInfoAboutURL(data.getUrl().toString());
            if (urlData == null) {
                continue;
            }
            OffsetDateTime timeEdit = clientManager.timeEditLinkForType(urlData);
            if (!timeEdit.equals(data.getLastEditTime())) {
                Integer countAnswerNow = clientManager.getCountAnswer(urlData);
                String description = "";
                if (countAnswerNow > data.getCountAnswer()) {
                    description = clientManager.getInfoCountByType(urlData.getType());
                }
                List<DataUserLinks> dataUserLinks = jdbcLinkService.findUserLinksByLinks(data.getId());
                botClient.updater(new LinkUpdateRequest(data.getId(), data.getUrl(), description,
                        dataUserLinks.stream().map(DataUserLinks::getUserId).toList()));
            }
        }
        log.info("Update info about urls");
    }
}
