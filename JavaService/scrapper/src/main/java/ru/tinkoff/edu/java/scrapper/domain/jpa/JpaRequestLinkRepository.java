package ru.tinkoff.edu.java.scrapper.domain.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.LinkEntity;

import java.util.List;

@Repository
public interface JpaRequestLinkRepository extends JpaRepository<LinkEntity, Long> {

    List<LinkEntity> getLinkEntityByUrl(String url);
}
