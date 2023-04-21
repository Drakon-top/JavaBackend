package ru.tinkoff.app.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.app.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.entity.ClientEntity;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserWithInfo;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ScrapperApplication.class)
public class JpaClientServiceTest extends IntegrationEnvironment {

    private final URI TEST_URL = new URI("https://github.com/person/rep/");
    private final String USER_NAME = "Alex";
    private final Long CHAT_ID = 13424L;
    private final String DEFAULT_USER_STATE = "NONE";

    @Autowired
    private TgChatService jpaTgChatService;
    @Autowired
    private JpaRequestClientRepository jpaRequestClientRepository;

    public JpaClientServiceTest() throws URISyntaxException {

    }

    @Transactional
    @Rollback
    @Test
    public void register__addUser_DBHaveUser() {
        jpaTgChatService.register(CHAT_ID, USER_NAME);

        DataUserWithInfo entity = jpaTgChatService.getUser(CHAT_ID);

        assertAll(
                () -> assertEquals(CHAT_ID, entity.getChatId()),
                () -> assertEquals(USER_NAME, entity.getUserName())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void unregister__deleteUser_DBHaveUser() {
        jpaTgChatService.register(CHAT_ID, USER_NAME);

        jpaTgChatService.unregister(CHAT_ID);

        Optional<ClientEntity> clientEntity = jpaRequestClientRepository.findById(CHAT_ID);

        assertAll(
                () -> assertFalse(clientEntity.isPresent())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void updateStateUser__addUserUpdateStage_StageChange() {
        String newUserStage = "TRACK";
        jpaTgChatService.register(CHAT_ID, USER_NAME);
        DataUserWithInfo entityWas = jpaTgChatService.getUser(CHAT_ID);

        jpaTgChatService.updateStateUser(CHAT_ID, newUserStage);

        DataUserWithInfo entityNow = jpaTgChatService.getUser(CHAT_ID);
        assertAll(
                () -> assertEquals(entityWas.getUserState(), DEFAULT_USER_STATE),
                () -> assertEquals(entityNow.getUserState(), newUserStage)
        );
    }

    @Transactional
    @Rollback
    @Test
    public void getUser__addUser_UserEqualsData() {
        jpaTgChatService.register(CHAT_ID, USER_NAME);

        DataUserWithInfo entity = jpaTgChatService.getUser(CHAT_ID);

        assertAll(
                () -> assertEquals(CHAT_ID, entity.getChatId()),
                () -> assertEquals(USER_NAME, entity.getUserName()),
                () -> assertEquals(DEFAULT_USER_STATE, entity.getUserState())
        );
    }
}
