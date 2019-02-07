package com.example.rholbrook.tickettoride.gamelobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.shared.model.Player;

import java.util.ArrayList;

public class GameLobbyActivity extends AppCompatActivity implements
        GameLobbyActivityContract.View {

    private GameLobbyActivityContract.Presenter mPresenter;

    private RecyclerView playerRecyclerView;
    private RecyclerView chatRecyclerView;
    private Button chatSendButton;
    private EditText chatEditText;
    private Button adminStartGameButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_lobby);
//        String gameId = savedInstanceState.getString("gameId");
//        String hostUsername = savedInstanceState.getString("hostUsername");
        playerRecyclerView = findViewById(R.id.current_player_recycler_view);
        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatSendButton = findViewById(R.id.message_send_button);
        chatEditText = findViewById(R.id.chat_edit_text);
        adminStartGameButton = findViewById(R.id.admin_start_game_button);

        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sendChat(chatEditText.getText().toString());
            }
        });

        mPresenter = new GameLobbyActivityPresenter(this);
        mPresenter.init();
//        mPresenter.setHost(hostUsername);
//        mPresenter.setGameId(gameId);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setHostStartButtonUsername(boolean isHost) {
        adminStartGameButton.setEnabled(isHost);
        if (isHost) {
            adminStartGameButton.setVisibility(View.VISIBLE);
            adminStartGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.startGame();
                }
            });
        } else {
            adminStartGameButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updatePlayerList(ArrayList<Player> connectedPlayers) {
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playerRecyclerView.setAdapter(new PlayerListAdapter(connectedPlayers, this));
    }

    @Override
    public void updateChatList(ArrayList<ChatModel> chatMessages) {
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playerRecyclerView.setAdapter(new ChatAdapter(chatMessages, this));
    }

    @Override
    public void startGameActivity(String gameId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameId", gameId);
        startActivity(intent);
    }
}
