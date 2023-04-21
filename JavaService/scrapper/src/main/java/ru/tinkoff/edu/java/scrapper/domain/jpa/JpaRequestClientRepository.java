package ru.tinkoff.edu.java.scrapper.domain.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface JpaRequestClientRepository extends JpaRepository<ClientEntity, Long> {

    void deleteClientEntitiesById(Long id);
}
