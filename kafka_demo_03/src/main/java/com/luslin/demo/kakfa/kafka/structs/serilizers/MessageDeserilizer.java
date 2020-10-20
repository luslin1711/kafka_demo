package com.luslin.demo.kakfa.kafka.structs.serilizers;

import com.luslin.demo.kakfa.kafka.structs.Message;
import com.luslin.demo.kakfa.kafka.util.ProtostuffUtil;
import org.apache.kafka.common.serialization.Deserializer;


public class MessageDeserilizer implements Deserializer<Message> {
    @Override
    public Message deserialize(String topic, byte[] data) {
        return ProtostuffUtil.deserializer(data, Message.class);
    }
}
