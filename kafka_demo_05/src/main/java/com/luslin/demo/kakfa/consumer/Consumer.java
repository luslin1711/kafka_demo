package com.luslin.demo.kakfa.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class Consumer implements Runnable{


    protected String groupId;
    protected String topic;
    protected Properties properties;

    public Consumer(String groupId, String topic) {
        this.groupId = groupId;
        this.topic = topic;
        this.properties = getProperties();

    }
    protected Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupId);       // 组id，同一组下共享offset
        props.put("enable.auto.commit", "false");   // 关闭自动提交
        props.put("fetch.min.bytes", "512");        // 指定从服务器获取的最小字节数
        props.put("fetch.max.wait.ms", "100");      // 等待时间。在服务器数据不足fetch.min.bytes时， 达到时间也会返回
        props.put("auto.offset.reset", "latest"); // 在offset 失效的情况下，earliest表示从起始位置开始读取分区记录。  latest 表示从最新记录读取（在消费者启动后）
        return props;
    }

    public void run() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties, new StringDeserializer(), new StringDeserializer());
        try {
            consumer.subscribe(Collections.singletonList(topic));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                if (records.isEmpty()) continue;
                System.out.println("recordsLen: " + records.count());
                for ( ConsumerRecord <String, String> record : records ) {
                    System.out.println("threadId: " + Thread.currentThread().getName() +" ,groupId: " + groupId +", partition: " + record.partition());
                }
                consumer.commitAsync();
            }
        } finally {
            consumer.close();
        }

    }

}
