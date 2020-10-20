package com.luslin.demo.kakfa.consumer;

import java.util.concurrent.*;


public class ConsumerWithDifferentGroupId  extends Consumer{


    public ConsumerWithDifferentGroupId(String groupId, String topic) {
        super(groupId, topic);
    }

    public static void main(String[] args) throws InterruptedException {
        String topic = "topic05";
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            service.submit(new Consumer("group0" + i, topic));
        }

    }
}
