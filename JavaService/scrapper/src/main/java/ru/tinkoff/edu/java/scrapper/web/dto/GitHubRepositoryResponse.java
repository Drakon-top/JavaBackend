package ru.tinkoff.edu.java.scrapper.web.dto;

import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(String name, OffsetDateTime updatedAt) { }
