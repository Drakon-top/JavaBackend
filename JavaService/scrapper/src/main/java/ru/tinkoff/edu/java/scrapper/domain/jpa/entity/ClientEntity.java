package ru.tinkoff.edu.java.scrapper.domain.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity  {

    @Id
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_state", nullable = false)
    @NotNull
    private String userState;


    @ManyToMany
    @JoinTable(
            name = "user_links",
            joinColumns = @JoinColumn(name = "links_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<LinkEntity> userLinks = new HashSet<>();

}
