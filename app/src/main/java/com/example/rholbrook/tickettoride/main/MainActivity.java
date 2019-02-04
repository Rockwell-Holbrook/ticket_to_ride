package com.example.rholbrook.tickettoride.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Game;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements
        MainActivityContract.View,
        View.OnClickListener,
        CreateGameDialogFragment.CreateGameDialogInterface {

    private MainActivityContract.Presenter mPresenter;

    private Button createGameButton;
    private Button joinGameButton;
    private RecyclerView gameListRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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
    public void updateGameList(List<Game> games) {
        this.gameListRecyclerView.setAdapter(new GameListAdapter(games, this));
    }

    @Override
    public void createGame() {
        CreateGameDialogFragment dialog = new CreateGameDialogFragment();
        dialog.show(getSupportFragmentManager(), "CreateGameDialogFragment");
    }

    @Override
    public void selectGame(UUID gameNumber) {
        mPresenter.setSelectedGameId(gameNumber);
    }

    @Override
    public void onClick(View view) {
        mPresenter.onClick(view.getId());
    }

    @Override
    public void onCreatePressed(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onCancelPressed(DialogFragment dialog) {
        dialog.dismiss();
    }
}
