package com.luslin.demo.kakfa.kafka.producer;

import com.luslin.demo.kakfa.kafka.structs.Message;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SyncSendProducer extends AbstractProducer {

    public SyncSendProducer() throws ExecutionException, InterruptedException {
        init();
    }
    @Override
    protected void send() throws ExecutionException, InterruptedException {
        if (producer == null) throw new InterruptedException("producer is null");
        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String, Message>("topic03",
                    Integer.toString(i), new Message("m:" + i, "context:" + i)));
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println("offset: " + recordMetadata.offset() + ", partition: " + recordMetadata.partition());
        }
    }
}
