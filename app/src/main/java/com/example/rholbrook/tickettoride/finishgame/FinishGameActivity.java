package com.example.rholbrook.tickettoride.finishgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Player;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FinishGameActivity extends AppCompatActivity implements FinishGameActivityContract.View {
    private FinishGameActivityContract.Presenter mPresenter;

    private Button closeButton;
    private RecyclerView finishedGameRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static CountingIdlingResource finishGameActivityIdlingResource;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_finish_game);
        closeButton = findViewById(R.id.finish_close_button);
        finishedGameRecyclerView = findViewById(R.id.finish_game_players);
        finishGameActivityIdlingResource = new CountingIdlingResource("Finish Game Idling Resource");

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishGameActivityIdlingResource.increment();
                Intent intent = new Intent(FinishGameActivity.this, MainActivity.class);
                try {
                    ServerProxy.getInstance().connectToManagementSocket(Authentication.getInstance().getUsername());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                finishGameActivityIdlingResource.decrement();
                startActivity(intent);
                FinishGameActivity.this.finish();
            }
        });


        mLayoutManager = new LinearLayoutManager(this);
        finishedGameRecyclerView.setHasFixedSize(true);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        finishedGameRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter = new FinishGameActivityPresenter(this);
        mPresenter.init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void updateData(List<Player> players) {
        ArrayList<Player> playerList = new ArrayList<>(players);
        finishedGameRecyclerView.setAdapter(new FinishGamePlayersAdapter(playerList));
    }

    public static CountingIdlingResource getIdlingResourceInTest() {
        return finishGameActivityIdlingResource;
    }

}
