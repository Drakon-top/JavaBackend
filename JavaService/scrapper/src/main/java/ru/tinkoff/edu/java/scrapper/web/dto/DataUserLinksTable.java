package ru.tinkoff.edu.java.scrapper.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataUserLinksTable {
    private Long userId;
    private Long linksId;
}
