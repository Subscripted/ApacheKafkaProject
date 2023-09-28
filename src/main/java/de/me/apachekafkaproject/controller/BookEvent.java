package de.me.apachekafkaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

public class BookEvent {

    private String title;
    private String description;
    private Double price;

    //  standard constructors, getters and setters

    private static final String TOPIC = "books";

    @Autowired
    private KafkaTemplate<String, BookEvent> bookEventKafkaTemplate;

    public void sentBookEvent(BookEvent book){
        bookEventKafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), book);
    }
}