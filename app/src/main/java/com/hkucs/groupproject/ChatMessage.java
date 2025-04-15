package com.hkucs.groupproject;

public class ChatMessage {
    public enum Sender {
        USER, LLM
    }

    private String content;
    private Sender sender;

    public ChatMessage(String content, Sender sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public Sender getSender() {
        return sender;
    }
}