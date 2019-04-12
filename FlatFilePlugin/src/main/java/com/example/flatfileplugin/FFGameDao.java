package com.example.flatfileplugin;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.model.Game;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FFGameDao implements IGameDao {
    private Gson gson = new Gson();

    @Override
    public void saveGame(Game game) throws IOException {
        Path dir = Paths.get("C:\\Users\\Blaine Johnson\\StudioProjects\\ticket_to_ride\\FFDatabase\\games");
        Path gamePath = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path: files) {
                String fileName = path.getFileName().toString();
                if (fileName.equals(game.getGameId())) {
                    gamePath = path;
                    break;
                }
            }
            if (gamePath != null) {
                Files.delete(gamePath);
            }
            gamePath = Files.createFile(Paths.get(dir.toString(), game.getGameId()));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        try (FileWriter writer = new FileWriter(gamePath.toFile())) {
            writer.write(gson.toJson(game));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void saveDelta(String gameid, Command delta) throws IOException {
        List<Command> deltas;
        Path dir = Paths.get("C:\\Users\\Blaine Johnson\\StudioProjects\\ticket_to_ride\\FFDatabase\\deltas");
        Path deltaPath = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path: files) {
                String fileName = path.getFileName().toString();
                if (fileName.equals(gameid)) {
                    deltaPath = path;
                    break;
                }
            }
            deltas = new ArrayList<>();
            if (deltaPath != null) {
                deltas = getDeltas(gameid);
                Files.delete(deltaPath);
            }
            deltas.add(delta);
            deltaPath = Files.createFile(Paths.get(dir.toString(), gameid));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        try (FileWriter writer = new FileWriter(deltaPath.toFile())) {
            writer.write(gson.toJson(deltas));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Game getGame(String gameid) throws IOException {
        Path dir = Paths.get("C:\\Users\\Blaine Johnson\\StudioProjects\\ticket_to_ride\\FFDatabase\\games");
        Path gamePath = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path: files) {
                String fileName = path.getFileName().toString();
                if (fileName.equals(gameid)) {
                    gamePath = path;
                    break;
                }
            }
            if (gamePath == null) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        try (FileReader reader = new FileReader(gamePath.toFile())) {

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    @Override
    public List<Command> getDeltas(String gameid) {
        return null;
    }

    @Override
    public void clear() {

    }
}
