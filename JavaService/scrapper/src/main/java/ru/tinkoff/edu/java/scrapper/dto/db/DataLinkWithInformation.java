package ru.tinkoff.edu.java.scrapper.dto.db;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataLinkWithInformation {
    private Long id;
    private URI url;
    private OffsetDateTime lastUpdate;
    private OffsetDateTime lastEditTime;
    private Integer countAnswer;
}
