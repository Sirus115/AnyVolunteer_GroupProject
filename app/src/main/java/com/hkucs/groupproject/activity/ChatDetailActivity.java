package com.hkucs.groupproject.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hkucs.groupproject.adapter.ChatAdapter;
import com.hkucs.groupproject.ChatMessage;
import com.hkucs.groupproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity {

    private RecyclerView rvChatDetail;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

        String chatId = getIntent().getStringExtra("chat_id");

        rvChatDetail = findViewById(R.id.rvChatDetail);
        rvChatDetail.setLayoutManager(new LinearLayoutManager(this));

        // 模拟加载历史聊天记录（根据 chatId 加载）
        chatMessages = new ArrayList<>();

        // 示例对话内容
        chatMessages.add(new ChatMessage("你好，我是你的 AI 助手。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("请帮我总结这段文字。", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("当然，请提供文字内容。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("这是原文：……", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("总结如下：……", ChatMessage.Sender.LLM));

        chatAdapter = new ChatAdapter(chatMessages);
        rvChatDetail.setAdapter(chatAdapter);
    }

}