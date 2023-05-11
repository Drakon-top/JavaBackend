package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.rabbitmq.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.service.send.SendNotificationService;
import ru.tinkoff.edu.java.scrapper.service.send.SendNotificationServiceRabbitImpl;

@Configuration
@EnableRabbit
@ConditionalOnProperty(prefix = "app", name = "useRabbitMQ", havingValue = "true")
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String address;
    @Value("${spring.rabbitmq.username}")
    private String userName;
    @Value("${spring.rabbitmq.password}")
    private String password;
    private static final String ARGUMENT_EXCHANGE_DLQ = "x-dead-letter-exchange";
    private static final String ARGUMENT_ROTING_KEY_DLQ = "x-dead-letter-routing-key";

    @Bean
    public Queue queue(ApplicationConfig config) {
        return QueueBuilder.durable(config.queue())
            .withArgument(ARGUMENT_EXCHANGE_DLQ, "")
            .withArgument(ARGUMENT_ROTING_KEY_DLQ, config.exchange() + ".dlq")
            .build();
    }

    @Bean
    public DirectExchange exchange(ApplicationConfig config) {
        return new DirectExchange(config.exchange(), true, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SendNotificationService getSendNotificationRabbitMQ(ScrapperQueueProducer scrapperQueueProducer) {
        return new SendNotificationServiceRabbitImpl(scrapperQueueProducer);
    }
}
