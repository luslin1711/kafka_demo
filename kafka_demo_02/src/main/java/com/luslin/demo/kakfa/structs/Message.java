package com.luslin.demo.kakfa.structs;


public class Message {
    String Id;
    String Context;

    public Message(String id, String context) {
        Id = id;
        Context = context;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Id='" + Id + '\'' +
                ", Context='" + Context + '\'' +
                '}';
    }
}
