package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.enums.AccessType;
import ru.tinkoff.edu.java.scrapper.web.sheduler.Scheduler;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test, @NotNull Scheduler scheduler, @NotNull AccessType databaseAccessType,
                                @NotNull String queue, @NotNull String exchange, @NotNull boolean useRabbitMQ) {
}
