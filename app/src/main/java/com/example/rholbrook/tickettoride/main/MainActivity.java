package com.example.rholbrook.tickettoride.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;

public class MainActivity extends AppCompatActivity implements
        MainActivityContract.View,
        View.OnClickListener {

    private MainActivityContract.Presenter mPresenter;

    Button createGameButton;
    Button joinGameButton;
    RecyclerView gameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        createGameButton = findViewById(R.id.create_game_button);
        createGameButton.setId(MainActivityModel.CREATE_GAME_BUTTON);
        joinGameButton = findViewById(R.id.join_game_button);
        joinGameButton.setId(MainActivityModel.JOIN_GAME_BUTTON);
        gameList = findViewById(R.id.game_list_recycler_view);

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
    public void updateGameList() {

    }

    @Override
    public void onClick(View view) {
        mPresenter.onClick(view.getId());
    }
}
