package com.luslin.demo.kakfa.kafka.structs.serilizers;

import com.luslin.demo.kakfa.kafka.structs.Message;
import com.luslin.demo.kakfa.kafka.util.ProtostuffUtil;
import org.apache.kafka.common.serialization.Serializer;

public class MessageSerializer implements Serializer<Message> {
    @Override
    public byte[] serialize(String topic, Message data) {
        return ProtostuffUtil.serializer(data, Message.class);
    }
}
