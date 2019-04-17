package com.example.rholbrook.tickettoride.gamelobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.chat.ChatAdapter;
import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.chat.ChatPresenter;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.rholbrook.tickettoride.serverconnection.ClientFacade;
import com.example.rholbrook.tickettoride.serverconnection.ServerDisconnectedDialogFragment;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameLobbyActivity extends AppCompatActivity implements
        GameLobbyActivityContract.View,
        ChatContract.ChatView,
        Observer {

    private static final int MINIMUM_CONNECTED_PLAYERS = 2;
    private GameLobbyActivityContract.Presenter mPresenter;
    private ChatContract.ChatPresenter chatPresenter;
    private DialogFragment serverDisconnectedFragment;
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
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(GameLobbyActivity.this));
        mPresenter = new GameLobbyActivityPresenter(this, this);
        chatPresenter = new ChatPresenter(this, GameLobbyActivityModel.getInstance());
        mPresenter.init();
        mPresenter.setGameId(gameId);
        try {
            mPresenter.getPlayerList();
        } catch (Exception e) {
            try {
                ServerProxy.getInstance().connectToGameSocket(gameId, Authentication.getInstance().getUsername());
                mPresenter.getPlayerList();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
        mPresenter.checkHost(hostUsername);
        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.sendChat(chatEditText.getText().toString());
                chatEditText.getText().clear();
            }
        });

        serverDisconnectedFragment = new ServerDisconnectedDialogFragment();

        ClientFacade.getInstance().addObserver(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        ClientFacade.getInstance().deleteObserver(this);
        super.onStop();
    }

    @Override
    public void setHostStartButtonUsername(boolean isHost) {
        adminStartGameButton.setEnabled(isHost);
        if (isHost) {
            adminStartGameButton.setVisibility(View.VISIBLE);
            adminStartGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                if(mPresenter.getConnectedPlayers().size() < MINIMUM_CONNECTED_PLAYERS) {
//                    Toast.makeText(GameLobbyActivity.this, "Cannot start game with less than 2 players", Toast.LENGTH_SHORT).show();
//                } else {
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
    public void updateChatList(List<Chat> chatMessages) {
        final List<Chat> messages = chatMessages;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                chatRecyclerView.setAdapter(new ChatAdapter(messages, chatPresenter, getApplicationContext()));
                chatRecyclerView.scrollToPosition(messages.size() - 1);
            }
        });
    }

    @Override
    public void startGameActivity(String gameId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameId", gameId);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showServerDisconnectedFragment() {
        Fragment fragmentA = getSupportFragmentManager().findFragmentByTag("Server Disconnected Fragment");
        if (fragmentA == null) {
            serverDisconnectedFragment.setCancelable(false);
            serverDisconnectedFragment.show(getSupportFragmentManager(), "Server Disconnected Fragment");
        }
    }

    @Override
    public void hideServerDisconnectedFragment() {
        serverDisconnectedFragment.dismiss();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (!ClientFacade.getInstance().isConnected()) {
            showServerDisconnectedFragment();
            while (!ClientFacade.getInstance().isConnected()) {
                try {
                    MainActivityModel.getInstance().connectToGameServer(mPresenter.getGameId());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            hideServerDisconnectedFragment();
        }
    }
}
