package ru.tinkoff.app.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tinkoff.app.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaTgChatService;
import ru.tinkoff.edu.java.scrapper.web.ClientManager;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ScrapperApplication.class)
public class JpaServiceTest extends IntegrationEnvironment {

    protected final URI TEST_URL = new URI("https://github.com/person/rep/");
    protected final String USER_NAME = "Alex";
    protected final Long CHAT_ID = 13424L;


    @Autowired
    protected TgChatService jpaTgChatService;

    @Autowired
    protected LinkService jpaLinkService;

    public JpaServiceTest() throws URISyntaxException {
    }
}
