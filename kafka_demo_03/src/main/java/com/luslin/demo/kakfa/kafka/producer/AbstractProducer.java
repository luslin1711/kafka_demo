package com.luslin.demo.kakfa.kafka.producer;

import com.luslin.demo.kakfa.kafka.structs.Message;
import com.luslin.demo.kakfa.kafka.structs.serilizers.MessageSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public abstract class AbstractProducer {
    KafkaProducer<String, Message> producer;

    public  void init() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("enable.idempotence", "true");
        props.put("retries", 5);
        props.put("max.in.flight.requests.per.connection", 1);
        producer = new KafkaProducer<>(props, new StringSerializer(), new MessageSerializer());
        send();
        producer.close();
    }
    protected abstract void send() throws ExecutionException, InterruptedException;
}
