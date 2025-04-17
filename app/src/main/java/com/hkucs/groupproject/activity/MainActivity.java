package com.hkucs.groupproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

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
    private Spinner spinnerModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChatHistory = findViewById(R.id.rvChatHistory);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        spinnerModels = findViewById(R.id.spinnerModels);

        // 初始化模型下拉框
        String[] modelNames = {"Model 1", "Model 2", "Model 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                modelNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModels.setAdapter(adapter);

        // 可选：监听模型选择变化
        spinnerModels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedModel = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "已选择模型：" + selectedModel, Toast.LENGTH_SHORT).show();
                // TODO: 根据选中的模型切换行为
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 不处理
            }
        });

        // 初始化聊天记录列表
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        rvChatHistory.setLayoutManager(new LinearLayoutManager(this));
        rvChatHistory.setAdapter(chatAdapter);

        // 接收 WelcomeActivity 传来的第一条消息（如果有）
        String firstMessage = getIntent().getStringExtra("first_message");
        if (firstMessage != null && !firstMessage.isEmpty()) {
            chatMessages.add(new ChatMessage(firstMessage, ChatMessage.Sender.USER));
            chatMessages.add(new ChatMessage("你好，我是你的 AI 助手。", ChatMessage.Sender.LLM));
        }

        // 模拟对话内容（测试用）
        chatMessages.add(new ChatMessage("你好！", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("你好，我是你的 AI 助手。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("请帮我总结这段文字。", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("当然可以，请提供文字内容。", ChatMessage.Sender.LLM));
        chatMessages.add(new ChatMessage("好的，这是内容：……", ChatMessage.Sender.USER));
        chatMessages.add(new ChatMessage("总结如下：……", ChatMessage.Sender.LLM));

        chatAdapter.notifyDataSetChanged();
        rvChatHistory.scrollToPosition(chatMessages.size() - 1);

        // 监听发送按钮
        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                chatMessages.add(new ChatMessage(text, ChatMessage.Sender.USER));
                chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                rvChatHistory.scrollToPosition(chatMessages.size() - 1);
                etMessage.setText("");

                // 模拟模型回复
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    chatMessages.add(new ChatMessage("模型回复：" + text, ChatMessage.Sender.LLM));
                    chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                    rvChatHistory.scrollToPosition(chatMessages.size() - 1);
                }, 500);
            }
        });

        // 历史记录按钮跳转
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        // 登录按钮跳转
        ImageButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}