package de.me.apachekafkaproject.config;

import de.me.apachekafkaproject.controller.BookEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BookEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ConsumerFactory<String, BookEvent> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();

        // required consumer factory properties

        return new DefaultKafkaConsumerFactory<>(props);


    }
    @KafkaListener(topics = "books", groupId = "book-notification-consumer", concurrency = "2")
    public void bookNotificationConsumer(BookEvent event) {
        logger.info("Books event received for notification => {}", event);
    }
}