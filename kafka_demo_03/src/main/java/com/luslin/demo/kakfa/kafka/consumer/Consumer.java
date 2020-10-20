package com.luslin.demo.kakfa.kafka.consumer;

import com.luslin.demo.kakfa.kafka.structs.Message;
import com.luslin.demo.kakfa.kafka.structs.serilizers.MessageDeserilizer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class Consumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "consumers01");       // 组id，同一组下共享offset
        props.put("enable.auto.commit", "false");   // 关闭自动提交
        props.put("fetch.min.bytes", "512");        // 指定从服务器获取的最小字节数
        props.put("fetch.max.wait.ms", "100");      // 等待时间。在服务器数据不足fetch.min.bytes时， 达到时间也会返回
        props.put("auto.offset.reset", "earliest"); // 在offset 失效的情况下，earliest表示从起始位置开始读取分区记录。  latest 表示从最新记录读取（在消费者启动后）

        KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(props, new StringDeserializer(), new MessageDeserilizer());
        try {
            consumer.subscribe(Collections.singletonList("topic03"));
            while (true) {
                ConsumerRecords<String, Message> records = consumer.poll(Duration.ofMillis(100));
                if (records.isEmpty()) continue;
                System.out.println("recordsLen: " + records.count());
                for ( ConsumerRecord <String, Message> record : records ) {
                    System.out.println("partition: " + record.partition() + " message: " + record.value()  + " offset: " + record.offset() );
                }
                consumer.commitAsync();
            }
        } finally {
            consumer.close();
        }

    }
}
