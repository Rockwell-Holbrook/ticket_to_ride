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
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.gamelobby.ChatAdapter;
import com.example.shared.model.Chat;

import java.util.List;

public class ChatFragment extends Fragment implements DrawerContract.ChatView {
    private DrawerContract.ChatPresenter presenter;
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
        presenter = new ChatFragmentPresenter(this);
        chatRecyclerView = view.findViewById(R.id.chat_recycler_view);
        sendButton = view.findViewById(R.id.message_send_button);
        sendButton.setEnabled(true);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked and stuff", Toast.LENGTH_LONG);
                presenter.sendChat(editText.getText().toString());
            }
        });
        editText = view.findViewById(R.id.chat_edit_text);
        presenter.getChatHistory();
        return view;
    }

    @Override
    public void updateChatList (List<Chat> chatMessages) {
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRecyclerView.setAdapter(new ChatAdapter(chatMessages));
    }
}
