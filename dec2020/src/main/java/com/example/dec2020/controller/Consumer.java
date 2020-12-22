package com.example.dec2020.controller;

import com.example.dec2020.persist.Message;
import com.example.dec2020.persist.MessageService;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class Consumer {

  @Autowired
  MessageService messageService;

  @RequestMapping(path = "/recievemessage",method = RequestMethod.GET )
  public void RecieveMessage(){
    String topicName = "topic1";
    Properties props = new Properties();

    props.put("bootstrap.servers", "localhost:9092");
    props.put("group.id", "test-group");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    KafkaConsumer<String, String> consumer = new KafkaConsumer
        <String, String>(props);

    //Kafka Consumer subscribes list of topics here.
    consumer.subscribe(Arrays.asList(topicName));

    //print the topic name
    System.out.println("Subscribed to topic - " + topicName);

    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(100);
      for (ConsumerRecord<String, String> record : records) {

        // print the topic, partition, offset, timestamp, key and value for the consumer records.
        System.out.printf(
            "topic = %s, partition = %d, offset = %d, timestamp = %d, key = %s, value = %s\n",
            record.topic(), record.partition(), record.offset(), record.timestamp(), record.key(),
            record.value());
        Message message = new Message();
        message.setMessage_id(record.offset());
        message.setData(record.topic() +":"+ record.value());
        messageService.saveOrUpdate(message);

      }
    }

  }
}
