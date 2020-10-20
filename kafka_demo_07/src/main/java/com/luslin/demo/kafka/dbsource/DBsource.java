package com.luslin.demo.kafka.dbsource;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBsource {

    Map<Integer, Long> source = new ConcurrentHashMap<>();

    public void save(Integer partition, Long offset) {source.put(partition, offset);}

    public void save(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        for (Map.Entry<TopicPartition, OffsetAndMetadata> topicPartitionOffsetAndMetadataEntry : currentOffsets.entrySet()) {
                save(topicPartitionOffsetAndMetadataEntry.getKey().partition(), topicPartitionOffsetAndMetadataEntry.getValue().offset());
        }
    }

    public Long search(Integer partition) {return source.get(partition);}
}
