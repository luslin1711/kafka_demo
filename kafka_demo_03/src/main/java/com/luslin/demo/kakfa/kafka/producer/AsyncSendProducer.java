package com.luslin.demo.kakfa.kafka.producer;

import com.luslin.demo.kakfa.kafka.structs.Message;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

public class AsyncSendProducer extends AbstractProducer {

    public AsyncSendProducer() throws ExecutionException, InterruptedException {
        init();
    }
    @Override
    protected void send() throws ExecutionException, InterruptedException {
        if (producer == null) throw new InterruptedException("producer is null");
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, Message>("topic03",
                    Integer.toString(i), new Message("m:" + i, "context:" + i)), new CallBackHandler());
        }
    }
}
