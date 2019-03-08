package com.example.rholbrook.tickettoride.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.chat.ChatAdapter;
import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.chat.ChatPresenter;
import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.rholbrook.tickettoride.game.HistoryContract;
import com.example.shared.model.Chat;

import java.util.List;

public class ChatFragment extends Fragment implements ChatContract.ChatView {
    private ChatContract.ChatPresenter presenter;
    private RecyclerView chatRecyclerView;
    private Button sendButton;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        presenter = new ChatPresenter(this, GameActivityModel.getInstance());
        chatRecyclerView = view.findViewById(R.id.chat_recycler_view);
        sendButton = view.findViewById(R.id.drawer_chat_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendChat(editText.getText().toString());
            }
        });
        editText = view.findViewById(R.id.chat_edit_text);
        presenter.getChatHistory();
        return view;
    }

    @Override
    public void updateChatList (List<Chat> chatMessages) {
        final List<Chat> messages = chatMessages;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                chatRecyclerView.setAdapter(new ChatAdapter(messages, presenter, getContext()));
                chatRecyclerView.scrollToPosition(messages.size() - 1);
            }
        });
    }
}
