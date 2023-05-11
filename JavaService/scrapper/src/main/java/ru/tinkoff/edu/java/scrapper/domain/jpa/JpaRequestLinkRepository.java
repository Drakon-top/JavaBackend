package ru.tinkoff.edu.java.scrapper.domain.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.LinkEntity;


public interface JpaRequestLinkRepository extends JpaRepository<LinkEntity, Long> {

    List<LinkEntity> getLinkEntityByUrl(String url);
}
