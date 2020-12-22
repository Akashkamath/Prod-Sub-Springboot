package com.example.dec2020.controller;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MessageProducer {

	@RequestMapping(path = "/sendmessage",method = RequestMethod.GET )
	public String sendMessage(){
		// Check arguments length value
//		if (args.length == 0) {
//			System.out.println("Creating topic name: TestTopic1");
//			return;
//		}

		// Assign topicName to string variable
		String topicName = "topic1";

		System.out.println("Creating topic name: TestTopic1");
		System.out.println("");
		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", 16384);

		// Reduce the no of requests less than 0
		props.put("linger.ms", 1);

		// The buffer.memory controls the total amount of memory available to the
		// producer for buffering.
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		for (int i = 0; i < 10; i++) {
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i),
					"Test Message: " + Integer.toString(i)));
		}
		System.out.println("Message sent successfully");

		// close the producer
		producer.close();
		return "Message sent successfully";
	}

}
