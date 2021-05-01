package com.exercise.file.filelistener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void publish(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}
}