package com.luslin.demo.kakfa.kafka.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class CallBackHandler implements Callback {

    private KafkaProducer producer;
    private ProducerRecord record;
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            System.out.println("send error: " + exception.getMessage());
        } else {
            System.out.println("send success, partition:" + metadata.partition() + ", offset: " + metadata.offset());
        }
    }
}
