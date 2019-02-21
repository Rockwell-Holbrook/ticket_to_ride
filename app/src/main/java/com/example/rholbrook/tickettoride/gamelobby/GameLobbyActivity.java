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
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.shared.model.Player;

import java.util.ArrayList;

public class GameLobbyActivity extends AppCompatActivity implements
        GameLobbyActivityContract.View {

    private static final int MINIMUM_CONNECTED_PLAYERS = 2;
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
        String gameId = null;
        String hostUsername = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameId = extras.getString("gameId");
            hostUsername = extras.getString("hostUsername");
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        playerRecyclerView = findViewById(R.id.current_player_recycler_view);
        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatSendButton = findViewById(R.id.message_send_button);
        chatEditText = findViewById(R.id.chat_edit_text);
        adminStartGameButton = findViewById(R.id.admin_start_game_button);

        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.sendChat(chatEditText.getText().toString());
            }
        });

        playerRecyclerView.setLayoutManager(new LinearLayoutManager(GameLobbyActivity.this));
        mPresenter = new GameLobbyActivityPresenter(this);
        mPresenter.init();
        mPresenter.setGameId(gameId);
        mPresenter.getPlayerList();
        mPresenter.checkHost(hostUsername);
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
//                    if(mPresenter.getConnectedPlayers().size() < MINIMUM_CONNECTED_PLAYERS) {
//                        Toast.makeText(GameLobbyActivity.this, "Cannot start game with less than 2 players", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(GameLobbyActivity.this, "Start Game", Toast.LENGTH_SHORT).show();
//                    }
                    mPresenter.startGame();
                }
            });
        } else {
            adminStartGameButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updatePlayerList(ArrayList<Player> connectedPlayers) {
        final ArrayList<Player> playerList = connectedPlayers;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playerRecyclerView.setAdapter(new PlayerListAdapter(playerList, GameLobbyActivity.this));
            }
        });

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
