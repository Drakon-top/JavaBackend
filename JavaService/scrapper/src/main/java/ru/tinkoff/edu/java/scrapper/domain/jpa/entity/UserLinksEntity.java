package ru.tinkoff.edu.java.scrapper.domain.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//@Table(name = "user_links")
public class UserLinksEntity {


    private Long userId;
    private Long linkId;
}
