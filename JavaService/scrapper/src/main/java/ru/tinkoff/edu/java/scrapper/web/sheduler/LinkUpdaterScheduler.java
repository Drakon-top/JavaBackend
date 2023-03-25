package ru.tinkoff.edu.java.scrapper.web.sheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkUpdaterScheduler {
    static final Logger log =
            LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    @Scheduled(fixedDelayString = "#{schedulerIntervalMs}")
    public void update() {
        log.info("Update info about urls");
    }
}
