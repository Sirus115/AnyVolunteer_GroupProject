package com.hkucs.groupproject.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hkucs.groupproject.ChatSummary;
import com.hkucs.groupproject.adapter.HistoryAdapter;
import com.hkucs.groupproject.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rvHistoryList;
    private List<ChatSummary> historyList;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistoryList = findViewById(R.id.rvHistoryList);
        rvHistoryList.setLayoutManager(new LinearLayoutManager(this));

        // 模拟历史记录
        historyList = new ArrayList<>();
        historyList.add(new ChatSummary("你好，我是 AI 助手...", "2024-04-15 15:34", "chat001"));
        historyList.add(new ChatSummary("请总结以下内容...", "2024-04-14 18:20", "chat002"));
        historyList.add(new ChatSummary("翻译这段话...", "2024-04-13 10:05", "chat003"));

        adapter = new HistoryAdapter(this, historyList);
        rvHistoryList.setAdapter(adapter);
    }

}