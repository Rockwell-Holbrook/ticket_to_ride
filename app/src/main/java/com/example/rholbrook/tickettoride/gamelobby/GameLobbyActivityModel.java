package com.example.rholbrook.tickettoride.gamelobby;

public class GameLobbyActivityModel {
    private static GameLobbyActivityModel instance;
    private GameLobbyActivityContract.Presenter mListener;

    private String hostUsername;
    private String gameId;

    public GameLobbyActivityModel() {
    }

    public static GameLobbyActivityModel getInstance() {
        if (instance == null) {
            instance = new GameLobbyActivityModel();
        }
        return instance;
    }

    public GameLobbyActivityContract.Presenter getmListener() {
        return mListener;
    }

    public void setmListener(GameLobbyActivityContract.Presenter mListener) {
        this.mListener = mListener;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
