package com.example.rholbrook.tickettoride.finishgame;

import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class FinishGameActivityPresenter implements FinishGameActivityContract.Presenter {
    private FinishGameActivityContract.View viewCallback;
    private GameActivityModel mModel;

    public FinishGameActivityPresenter(FinishGameActivityContract.View finishGameActivity) {
        this.viewCallback = finishGameActivity;
        this.mModel = GameActivityModel.getInstance();
    }

    @Override
    public void init() {
        ArrayList<Player> finishedPlayers = new ArrayList<>();
        if (mModel.getClient() != null) {
            finishedPlayers.add(mModel.getClient());
        }
        if (mModel.getOpponentOne() != null) {
            finishedPlayers.add(mModel.getOpponentOne());
        }
        if (mModel.getOpponentTwo() != null) {
            finishedPlayers.add(mModel.getOpponentTwo());
        }
        if (mModel.getOpponentThree() != null) {
            finishedPlayers.add(mModel.getOpponentThree());
        }
        if (mModel.getOpponentFour() != null) {
            finishedPlayers.add(mModel.getOpponentFour());
        }
        viewCallback.updateData(finishedPlayers);
    }
}
