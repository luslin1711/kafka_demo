package server.util;

import org.apache.kafka.common.serialization.Serializer;
import server.structs.Message;

public class MessageSerializer implements Serializer<Message> {
    @Override
    public byte[] serialize(String topic, Message data) {
        return ProtostuffUtil.serializer(data, Message.class);
    }
}
