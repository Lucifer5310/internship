package com.example.internship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "image-events", groupId = "internship-group")
    public void listen(String message) {
        log.info(message);
    }
}
