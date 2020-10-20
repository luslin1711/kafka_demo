package com.luslin.demo.kakfa.kafka.producer;

import java.util.concurrent.ExecutionException;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("sync producer");
        new SyncSendProducer();
        System.out.println("async producer");
        new AsyncSendProducer();
    }
}
