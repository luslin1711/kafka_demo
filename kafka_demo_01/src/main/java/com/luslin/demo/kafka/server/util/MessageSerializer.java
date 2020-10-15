package com.luslin.demo.kafka.server.util;

import com.luslin.demo.kafka.server.structs.Message;
import org.apache.kafka.common.serialization.Serializer;

public class MessageSerializer implements Serializer<Message> {
    @Override
    public byte[] serialize(String topic, Message data) {
        return ProtostuffUtil.serializer(data, Message.class);
    }
}
