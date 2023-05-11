package ru.tinkoff.edu.java.scrapper.dto.db;

import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataLink {
    private Long id;
    private URI url;
}
