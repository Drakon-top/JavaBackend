package ru.tinkoff.app.jpa;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.app.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.ClientEntity;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ScrapperApplication.class)
public class JpaLinksServiceTest extends IntegrationEnvironment {

    private final URI TEST_URL = new URI("https://github.com/person/newrep/");
    private final String TEST_URL_FOR_LIST = "https://github.com/person/rep";
    private final String USER_NAME = "Alex";
    private final Long CHAT_ID = 13424L;
    private final String DEFAULT_USER_STATE = "NONE";

    @Autowired
    private LinkService jpaLinkService;

    @Autowired
    private TgChatService jpaTgChatService;

    @Autowired
    private JpaRequestClientRepository jpaRequestClientRepository;

    @Autowired
    private JpaRequestLinkRepository jpaRequestLinkRepository;


    public JpaLinksServiceTest() throws URISyntaxException {
    }

    @Transactional
    @Test
    @Rollback
    public void add__AddLinksForUser_UserHaveLink() {
        jpaTgChatService.register(CHAT_ID, USER_NAME);
        DataLink dataLink = jpaLinkService.add(CHAT_ID, TEST_URL);

        ClientEntity client = jpaRequestClientRepository.getReferenceById(CHAT_ID);
        LinkEntity link = jpaRequestLinkRepository.getReferenceById(dataLink.getId());

        assertAll(
                () -> assertTrue(client.getUserLinks().stream().map(LinkEntity::getId)
                        .collect(Collectors.toSet()).contains(dataLink.getId())),
                () -> assertTrue(link.getUsers().stream().map(ClientEntity::getId)
                        .collect(Collectors.toSet()).contains(CHAT_ID))
        );

    }

    @Transactional
    @Test
    @Rollback
    public void remove__RemoveLinksForUser_UserDontHaveLink() {
        jpaTgChatService.register(CHAT_ID, USER_NAME);
        jpaLinkService.add(CHAT_ID, TEST_URL);

        DataLink dataLink = jpaLinkService.remove(CHAT_ID, TEST_URL);
        ClientEntity client = jpaRequestClientRepository.getReferenceById(CHAT_ID);

        assertAll(
                () -> assertFalse(client.getUserLinks().stream().map(LinkEntity::getId)
                        .collect(Collectors.toSet()).contains(dataLink.getId()))
        );

    }

    @Transactional
    @Test
    @Rollback
    public void listLinkAll__AddFifeLink_UserHaveLink() throws URISyntaxException {
        jpaTgChatService.register(CHAT_ID, USER_NAME);
        List<DataLink> dataLinks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataLinks.add(jpaLinkService.add(CHAT_ID, new URI(TEST_URL_FOR_LIST + String.valueOf(i))));
        }

        Collection<DataLink> dataLinkCollection = jpaLinkService.listLinkAll(CHAT_ID);

        for (DataLink dataLink : dataLinks) {
            assertTrue(dataLinkCollection.contains(dataLink));
        }
    }

    @Transactional
    @Test
    @Rollback
    public void findUserLinksByLinks__GetListUserByLinks_UserInList() throws URISyntaxException {
        jpaTgChatService.register(CHAT_ID, USER_NAME);
        DataLink dataLink = jpaLinkService.add(CHAT_ID, TEST_URL);

        List<DataUserLinks> dataUserLinks = jpaLinkService.findUserLinksByLinks(dataLink.getId());

        assertAll(
                () -> assertTrue(dataUserLinks.stream().map(DataUserLinks::getUserId)
                        .collect(Collectors.toSet()).contains(CHAT_ID))
        );
    }
}
