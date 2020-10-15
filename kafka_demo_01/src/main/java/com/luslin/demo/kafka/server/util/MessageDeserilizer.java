package com.luslin.demo.kafka.server.util;

import com.luslin.demo.kafka.server.structs.Message;
import org.apache.kafka.common.serialization.Deserializer;


public class MessageDeserilizer implements Deserializer<Message> {
    @Override
    public Message deserialize(String topic, byte[] data) {
        return ProtostuffUtil.deserializer(data, Message.class);
    }
}
