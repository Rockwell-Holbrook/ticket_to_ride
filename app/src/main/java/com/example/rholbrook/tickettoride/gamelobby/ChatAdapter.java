package com.example.rholbrook.tickettoride.gamelobby;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Chat> chats;

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_chat, viewGroup, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Chat chat = chats.get(viewHolder.getAdapterPosition());
        viewHolder.username.setText(chat.getUsername());
        viewHolder.message.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView message;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.chat_sender_username);
            message = itemView.findViewById(R.id.chat_sender_message);
        }
    }
}
