package com.example.flatfileplugin;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.model.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.CharBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FFGameDao implements IGameDao {
    private final String dblocation = "C:\\Users\\Blaine Johnson\\StudioProjects\\ticket_to_ride\\FFDatabase";
    private Gson gson = new Gson();

    @Override
    public void saveGame(Game game) throws IOException {
        Path dir = Paths.get(dblocation, "games");
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
        Path dir = Paths.get(dblocation, "deltas");
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
        Path dir = Paths.get(dblocation, "games");
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
        File file = gamePath.toFile();
        try (FileReader reader = new FileReader(file)) {
            CharBuffer buf = CharBuffer.allocate((int) file.length());      // Error if game exceeds maximum num bytes?
            int numRead = reader.read(buf);
            String json = buf.toString();
            return gson.fromJson(json, Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Command> getDeltas(String gameid) throws IOException {
        Path dir = Paths.get(dblocation,"deltas");
        Path deltaPath = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path: files) {
                String fileName = path.getFileName().toString();
                if (fileName.equals(gameid)) {
                    deltaPath = path;
                    break;
                }
            }
            if (deltaPath == null) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        File file = deltaPath.toFile();
        try (FileReader reader = new FileReader(file)) {
            CharBuffer buf = CharBuffer.allocate((int) file.length());      // Error if game exceeds maximum num bytes?
            int numRead = reader.read(buf);
            String json = buf.toString();
            Type type = new TypeToken<List<Command>>() {}.getType();
            return gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void clear() {
        File gameDir = new File(dblocation, "games");
        File[] gameFiles = gameDir.listFiles();
        for (int i = 0; i < gameDir.length(); i++) {
            gameFiles[i].delete();
        }
        File deltaDir = new File(dblocation, "deltas");
        File[] deltaFiles = deltaDir.listFiles();
        for (int i = 0; i < deltaDir.length(); i++) {
            deltaFiles[i].delete();
        }
    }
}
