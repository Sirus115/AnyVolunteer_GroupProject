package com.hkucs.groupproject;

public class ChatSummary {
    private String summary;
    private String timestamp;
    private String chatId; // 每条聊天唯一标识，可用于跳转

    public ChatSummary(String summary, String timestamp, String chatId) {
        this.summary = summary;
        this.timestamp = timestamp;
        this.chatId = chatId;
    }

    public String getSummary() {
        return summary;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getChatId() {
        return chatId;
    }
}