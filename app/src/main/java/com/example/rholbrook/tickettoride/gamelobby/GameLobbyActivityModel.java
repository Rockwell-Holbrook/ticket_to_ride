package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 *  GameLobbyActivityModel is the model of the Model-View-Presenter set of classes that
 *  control the game lobby. The game lobby is where players wait after joining a game
 *  but before the game begins. This class holds data about the game, players and chat
 *  messages. It includes methods for passing chat messages between the presenter and
 *  the server proxy, notifying the presenter of a change in players, and communicating
 *  between the presenter and server proxy when it is time to start the game. This class
 *  is a singleton.
 *
 * @author Blaine Johnson
 */
public class GameLobbyActivityModel extends Observable implements ChatContract.ChatModel {
    /**
     *  Single instance of the GameLobbyActivityModel
     */
    private static GameLobbyActivityModel instance;
    /**
     *  Reference to the presenter for the game lobby through which the model communicates
     *  with the view
     */
    private GameLobbyActivityContract.Presenter mListener;
    /**
     *  Reference to the presenter for the chat fragment portion of the game lobby
     */
    private ChatContract.ChatPresenter chatListener;
    /**
     *  Unique identifier for the game
     */
    private String gameId;
    /**
     *  List of players who have joined the game
     */
    private List<Player> connectedPlayers;
    /**
     *  List of all chat messages that have been received from the server
     */
    private List<Chat> chatMessages;

    /**
     * Iinitializes the connectedPlayers list and chatMessages list to empty lists
     *
     * @pre none
     * @post A reference to a new GameLobbyActivityModel will be returned
     */
    private GameLobbyActivityModel() {
        connectedPlayers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    /**
     * Returns the single instance of GameLobbyActivityModel, if it exits; creates
     * a new instance otherwise
     *
     * @return the single instance of GameLobbyActivityModel
     *
     * @pre none
     * @post There will be one and only one instance of GameLobbyActivityModel
     */
    public static GameLobbyActivityModel getInstance() {
        if (instance == null) {
            instance = new GameLobbyActivityModel();
        }
        return instance;
    }

    /**
     * Updates the chat message list and notifies the presenter of the change
     *
     * @param chat Chat object containing the message info.
     *
     * @pre chatMessages has been initialized to a non-null List
     * @pre chatListener is a valid reference to a ChatPresenter
     * @post size of chatMessages will be 1 greater than before the method call
     * @post The final element of chatMessages will be the chat parameter
     * @post The corresponding ChatPresenter will receive a call to updateChatList
     *          containing the list of messages
     */
    public void receivedChat(Chat chat) {
        chatMessages.add(chat);
        chatListener.updateChatList(chatMessages);
    }

    /**
     * Signals the server that it is time to start the game
     *
     * @pre methods getInstance and startGame are defined for class ServerProxy
     * @post ServerProxy will receive a call to startGame with the value of gameId
     */
    public void startGame() {
        ServerProxy.getInstance().startGame(gameId);
    }

    /**
     * Signals the presenter to start the game
     *
     * @pre mListener references a non-null game lobby Presenter
     * @pre method gameStarted is defined for Presenter
     * @post Presenter will receive a call to gameStarted
     */
    public void gameStarted() {
        mListener.gameStarted();
    }

    /**
     * Updates the connnectedPlayers list and notifies the presenter of the change
     *
     * @param playerList a Set of Player objects defining the updated set of players
     *
     * @pre playerList is non-null
     * @pre mListener references a non-null game lobby Presenter
     * @pre method updatePlayerList is defined for Presenter
     * @post Presenter will receive a call to updatePlayerList with the list of
     *          connectedPlayers
     * @post connectedPlayers will contain exactly the Player objects in playerList
     */
    public void newPlayerJoined(Set<Player> playerList) {
        this.connectedPlayers = new ArrayList<>(playerList);
        mListener.updatePlayerList(new ArrayList<>(playerList));
    }

    /**
     * Requests the server to send the connected player list
     *
     * @pre methods getInstance and getPlayerList are defined for class ServerProxy
     * @post ServerProxy will receive a call to getPlayerList with the value of gameId
     */
    public void getPlayerList() {
        ServerProxy.getInstance().getPlayerList(gameId);
    }

    /**
     * Sends a Chat object containing the given message to the server, specifying that
     * the chat is being sent before the game has started (i.e. from the lobby)
     *
     * @param message A String containing the message to be sent over chat
     *
     * @pre methods getInstance and getUsername are defined for the Authentication class
     * @pre getUsername returns a String object
     * @pre methods getInstance and sendChat are defined for the ServerProxy class
     * @post ServerProxy will receive a call to sendChat with a Chat object containing
     *       the player's username and message, the gameId, and the boolean false
     */
    @Override
    public void sendChat(String message) {
        Chat newChat = new Chat(Authentication.getInstance().getUsername(), message);
        ServerProxy.getInstance().sendChat(newChat, gameId, false);
    }

    /**
     * Returns the color associated with player with the given username
     *
     * @param username username of the player whose color is requested
     * @return PlayerColor enum object denoting the player's color, or null
     *          if the username does not match any connected players
     *
     * @pre username is a non-null String that matches a the username of one of the
     *      players in the connectedPlayers list
     * @pre methods getUsername and getPlayerColor are defined for class Player
     * @post A PlayerColor enum object will be returned, whose value equals that of the
     *      player in connectedPlayers whose username equals the given username
     */
    @Override
    public Player.PlayerColor getPlayerColor(String username) {
        for (Player player : connectedPlayers) {
            if (player.getUsername().equals(username)) {
                return player.getPlayerColor();
            }
        }
        return null;
    }

    /**
     * Sends the current chatMessages list to the ChatPresenter
     *
     * @pre chatListener references a non-null ChatPresenter
     * @pre method updateChatList is defined for  ChatPresenter
     * @pre chatMessages is non-null
     * @post ChatPresenter will receive a call to updateChatList containing the list
     *        of Chat objects
     */
    @Override
    public void getChatHistory() {
        chatListener.updateChatList(chatMessages);
    }

    public GameLobbyActivityContract.Presenter getmListener() {
        return mListener;
    }

    public void setmListener(GameLobbyActivityContract.Presenter mListener) {
        this.mListener = mListener;
    }

    @Override
    public void setChatListener(ChatContract.ChatPresenter chatListener) {
        this.chatListener = chatListener;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Player> getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(ArrayList<Player> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public void socketConnectionError(Exception ex) {
        mListener.socketConnectionError(ex);
    }
}
