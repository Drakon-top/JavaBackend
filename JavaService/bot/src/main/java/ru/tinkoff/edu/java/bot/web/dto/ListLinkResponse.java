package ru.tinkoff.edu.java.bot.web.dto;

import java.net.URI;
import java.util.List;

public record ListLinkResponse(List<URI> urls) { }
