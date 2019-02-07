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

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<ChatModel> chats;
    private GameLobbyActivity mListener;

    public ChatAdapter(ArrayList<ChatModel> chats, GameLobbyActivity mListener) {
        this.chats = chats;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_chat, viewGroup, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ChatModel chat = chats.get(viewHolder.getAdapterPosition());
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
