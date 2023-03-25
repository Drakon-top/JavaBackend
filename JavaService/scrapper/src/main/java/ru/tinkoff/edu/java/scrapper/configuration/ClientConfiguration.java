package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClientImpl;


@Configuration
public class ClientConfiguration {

    @Bean
    public GitHubClient getGitHubClient() {
        return new GitHubClientImpl();
    }

    @Bean
    public StackOverflowClient getStackOverflowClient() {
        return new StackOverflowClientImpl();
    }

    @Bean
    public long schedulerIntervalMs(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }
}
