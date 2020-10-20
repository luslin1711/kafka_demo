package com.luslin.demo.kakfa.consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultRebalanceListener implements ConsumerRebalanceListener {
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private org.apache.kafka.clients.consumer.Consumer consumer;

    public DefaultRebalanceListener(org.apache.kafka.clients.consumer.Consumer consumer) {
        this.consumer = consumer;
    }


    public void put(TopicPartition partition, OffsetAndMetadata offsetAndMetadata) {
        currentOffsets.put(partition, offsetAndMetadata);
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
        return currentOffsets;
    }


    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("Lost partitions in reblance. Committing current offsets: " + currentOffsets);
        consumer.commitSync(currentOffsets);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("get partitions :" + partitions.toString());
    }
}
