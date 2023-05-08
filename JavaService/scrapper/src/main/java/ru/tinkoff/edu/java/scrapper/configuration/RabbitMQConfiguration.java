package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("quest");
        cachingConnectionFactory.setPassword("quest");
        return cachingConnectionFactory;
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
