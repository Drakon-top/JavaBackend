package ru.tinkoff.edu.java.scrapper.domain.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "link")
public class LinkEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;

    @Column(name = "last_edit_time", nullable = false)
    private OffsetDateTime lastEditTime;

    @Column(name = "count_commit_or_question", nullable = false)
    private Integer countAnswer;

    @ManyToMany(mappedBy = "userLinks")
    private Set<ClientEntity> users = new HashSet<>();

}
