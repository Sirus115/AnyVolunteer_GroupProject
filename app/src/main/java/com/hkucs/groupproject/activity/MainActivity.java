package com.hkucs.groupproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hkucs.groupproject.adapter.ChatAdapter;
import com.hkucs.groupproject.ChatMessage;
import com.hkucs.groupproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChatHistory;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;
    private EditText etMessage;
    private ImageButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvChatHistory = findViewById(R.id.rvChatHistory);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        rvChatHistory.setLayoutManager(new LinearLayoutManager(this));
        rvChatHistory.setAdapter(chatAdapter);

        // 接收 WelcomeActivity 传来的第一条对话（如果有）
        String firstMessage = getIntent().getStringExtra("first_message");
        if (firstMessage != null && !firstMessage.isEmpty()) {
            chatMessages.add(new ChatMessage(firstMessage, ChatMessage.Sender.USER));
            chatMessages.add(new ChatMessage("你好，我是你的 AI 助手。", ChatMessage.Sender.LLM));
        }

        // TODO:下面仅作为测试前端时添加的模拟聊天记录
        chatMessages.add(new ChatMessage("你好！", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("你好，我是你的 AI 助手。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("请帮我总结这段文字。", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("当然可以，请提供文字内容。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("好的，这是内容：……", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("总结如下：……", ChatMessage.Sender.LLM));

        chatAdapter.notifyDataSetChanged();
        rvChatHistory.scrollToPosition(chatMessages.size() - 1);

        // 原来的发送按钮监听
        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                chatMessages.add(new ChatMessage(text, ChatMessage.Sender.USER));
                chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                rvChatHistory.scrollToPosition(chatMessages.size() - 1);
                etMessage.setText("");

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    chatMessages.add(new ChatMessage("模型回复：" + text, ChatMessage.Sender.LLM));
                    chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                    rvChatHistory.scrollToPosition(chatMessages.size() - 1);
                }, 500);
            }
        });

        // 历史记录页面跳转逻辑
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        ImageButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击跳转到 LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}