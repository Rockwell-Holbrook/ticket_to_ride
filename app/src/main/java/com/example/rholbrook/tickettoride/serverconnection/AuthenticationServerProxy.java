package com.example.rholbrook.tickettoride.serverconnection;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AuthenticationServerProxy {
    private static AuthenticationServerProxy instance;
    private static final int SERVER_PORT_NUMBER = 6666;
    private static final String SERVER_HOST = "ec2-3-19-11-239.us-east-2.compute.amazonaws.com";
    private static final String URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT_NUMBER;
    public static final String LOGIN_DESIGNATOR = "/authenticate/login";
    public static final String REGISTER_DESIGNATOR = "/authenticate/register";
    private static final String HTTP_POST = "POST";
    private String url;
    private static Gson gson = new Gson();

    public AuthenticationServerProxy() {}

    //Get instance of the singleton
    public static synchronized AuthenticationServerProxy getInstance() {
        if (instance == null) {
            instance = new AuthenticationServerProxy();
        }
        return instance;
    }

    public String login(User user) throws Throwable {
        HttpURLConnection connection = openConnection(AuthenticationServerProxy.LOGIN_DESIGNATOR);
        sendToServer(connection, user);

        Message response = getResponse(connection);
        if(!response.isSuccess()) {
            throw new Exception(response.getMessage());
        }
        String result = response.getMessage();
        return result;
    }

    public String register(User user) throws Throwable {
        HttpURLConnection connection = openConnection(AuthenticationServerProxy.REGISTER_DESIGNATOR);
        sendToServer(connection, user);

        Message response = getResponse(connection);
        if(!response.isSuccess()) {
            throw new Exception(response.getMessage());
        }
        String result = response.getMessage();
        return result;
    }

    private HttpURLConnection openConnection(String contextIdentifier) {
        HttpURLConnection result = null;
        try {
            URL url = new URL(URL_PREFIX + contextIdentifier);
            result = (HttpURLConnection)url.openConnection();
            result.setRequestMethod(HTTP_POST);
            result.setDoOutput(true);
            result.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void sendToServer(HttpURLConnection connection, Object objectToSend) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            gson.toJson(objectToSend, outputStreamWriter);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message getResponse(HttpURLConnection connection) throws Throwable {
        Message result = null;
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (connection.getContentLength() == -1) {
                    //-1 means that there is an unknown amount of information
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    result = (Message)gson.fromJson(inputStreamReader, Message.class);
                    inputStreamReader.close();
                }
            } else {
                throw new Exception(String.format("http code %d", connection.getResponseCode()));
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
