package com.hkucs.groupproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hkucs.groupproject.ChatMessage;
import com.hkucs.groupproject.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> chatList;

    public ChatAdapter(List<ChatMessage> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage message = chatList.get(position);

        if (message.getSender() == ChatMessage.Sender.USER) {
            holder.tvRight.setVisibility(View.VISIBLE);
            holder.tvRight.setText(message.getContent());
            holder.tvLeft.setVisibility(View.GONE);
        } else {
            holder.tvLeft.setVisibility(View.VISIBLE);
            holder.tvLeft.setText(message.getContent());
            holder.tvRight.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeft, tvRight;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tvLeftMessage);
            tvRight = itemView.findViewById(R.id.tvRightMessage);
        }
    }
}