package server.util;

import org.apache.kafka.common.serialization.Deserializer;
import server.structs.Message;


public class MessageDeserilizer implements Deserializer<Message> {
    @Override
    public Message deserialize(String topic, byte[] data) {
        return ProtostuffUtil.deserializer(data, Message.class);
    }
}
