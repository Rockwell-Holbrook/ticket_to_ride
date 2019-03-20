package com.example.rholbrook.tickettoride.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.gamelobby.GameLobbyActivity;
import com.example.rholbrook.tickettoride.login.LoginFragment;
import com.example.rholbrook.tickettoride.register.RegisterFragment;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        MainActivityContract.View,
        View.OnClickListener,
        CreateGameDialogFragment.CreateGameDialogInterface,
        JoinGameDialogFragment.JoinGameDialogInterface {

    private MainActivityContract.Presenter mPresenter;

    private Button createGameButton;
    private Button joinGameButton;
    private RecyclerView gameListRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FrameLayout fragmentContainer;
    private static CountingIdlingResource mainActivityIdlingResource;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentContainer = findViewById(R.id.lobby);
        createGameButton = findViewById(R.id.create_game_button);
        createGameButton.setId(MainActivityModel.CREATE_GAME_BUTTON);
        createGameButton.setOnClickListener(this);
        joinGameButton = findViewById(R.id.join_game_button);
        joinGameButton.setId(MainActivityModel.JOIN_GAME_BUTTON);
        joinGameButton.setOnClickListener(this);
        gameListRecyclerView = findViewById(R.id.game_list_recycler_view);
        gameListRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        gameListRecyclerView.setLayoutManager(mLayoutManager);
        joinGameButton.setEnabled(false);
        mainActivityIdlingResource = new CountingIdlingResource("Main Activity Idling Resource");

        mPresenter = new MainActivityPresenter(this);
        mPresenter.init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateGameList(ArrayList<Game> games) {
        final ArrayList<Game> gamesList = games;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.gameListRecyclerView.setAdapter(new GameListAdapter(gamesList, MainActivity.this));
            }
        });
    }

    @Override
    public void createGame() {
        CreateGameDialogFragment dialog = new CreateGameDialogFragment();
        dialog.show(getSupportFragmentManager(), "CreateGameDialogFragment");
    }

    @Override
    public void joinGame() {
        JoinGameDialogFragment dialog = new JoinGameDialogFragment();
        dialog.show(getSupportFragmentManager(), "JoinGameDialogFragment");
    }

    @Override
    public void selectGame(Game game) {
        joinGameButton.setEnabled(true);
        mPresenter.setSelectedGame(game);
    }

    @Override
    public void startGameLobbyFragment(final String gameId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!mainActivityIdlingResource.isIdleNow()) {
                    mainActivityIdlingResource.decrement();
                }
                Intent intent = new Intent(MainActivity.this, GameLobbyActivity.class);
                intent.putExtra("gameId", gameId);
                intent.putExtra("hostUsername", mPresenter.getSelectedGame().getHost().getUsername());
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        mPresenter.onClick(view.getId());
    }


    @Override
    public void onJoinPressed(DialogFragment dialog, Player.PlayerColor color) {
        mPresenter.joinGame(color);
        dialog.dismiss();
    }

    @Override
    public void onCreatePressed(DialogFragment dialog, String gameName, int maxPlayers, Player.PlayerColor selectedColor) {
        mPresenter.createGame(new Player(Authentication.getInstance().getUsername(), true, selectedColor), maxPlayers, gameName);
        dialog.dismiss();
    }

    @Override
    public void onCancelPressed(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onCreateError(CreateGameDialogFragment createGameDialogFragment, String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    public static CountingIdlingResource getIdlingResourceInTest() {
        return mainActivityIdlingResource;
    }
}
