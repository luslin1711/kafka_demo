package com.luslin.demo.kakfa.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("enable.idempotence", "true");
        props.put("retries", 5);
        props.put("max.in.flight.requests.per.connection", 1);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
        for (int i = 0; i < 100; i++) {
            Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String, String>("topic06", Integer.toString(i), Integer.toString(i)));
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println("offset: " + recordMetadata.offset() + ", partition: " + recordMetadata.partition());
        }
        producer.close();

    }
}
