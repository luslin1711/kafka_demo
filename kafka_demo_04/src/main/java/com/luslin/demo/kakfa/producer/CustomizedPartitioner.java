package com.luslin.demo.kakfa.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

import java.util.Map;

public class CustomizedPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer countForTopic = cluster.partitionCountForTopic(topic);
        if (countForTopic == null || countForTopic == 1) {
            return 0;
        } else {
            if (key == null) {
                return countForTopic-1;
            } else {
                return Utils.toPositive(Utils.murmur2(keyBytes)) % (countForTopic - 1);
            }
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
