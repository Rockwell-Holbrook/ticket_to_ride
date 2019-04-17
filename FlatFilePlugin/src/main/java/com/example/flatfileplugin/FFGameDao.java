package com.example.flatfileplugin;

import com.example.shared.commands.Command;
import com.example.shared.commands.Deltas;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.model.Game;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.CharBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FFGameDao implements IGameDao {
    private final String dblocation = "/Users/adamure/Documents/ticket_to_ride/FFDatabase";
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
    public void saveDelta(String gameid, String delta) throws IOException {
        Deltas deltas;
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
            deltas = new Deltas();
            if (deltaPath != null) {
                deltas = getDeltas(gameid);
                Files.delete(deltaPath);
            }
            deltas.addCmd(delta);

            if (gameid != null)
            deltaPath = Files.createFile(Paths.get(dir.toString(), gameid));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        if (deltaPath != null) {
            try (FileWriter writer = new FileWriter(deltaPath.toFile())) {
                writer.write(gson.toJson(deltas));
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
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
            String fileContents = "";
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                //do something with line
                fileContents += line;
            }
//            CharBuffer buf = CharBuffer.allocate((int) file.length());      // Error if game exceeds maximum num bytes?
//            int numRead = reader.read(buf);
//            String json = buf.toString();
            return gson.fromJson(fileContents, Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Game> getAllGames() throws Exception {
        List<Game> games = new ArrayList<>();

        Path dir = Paths.get(dblocation, "games");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path : files) {
                File file = path.toFile();
                try (FileReader reader = new FileReader(file)) {
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = null;
                    while((line = bufferedReader.readLine()) != null) {
                        //do something with line
                        games.add(gson.fromJson(line, Game.class));
                    }
//                    CharBuffer buf = CharBuffer.allocate((int) file.length());
//                    int numRead = reader.read(buf);
//                    String json = buf.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return games;
    }

    @Override
    public Deltas getDeltas(String gameid) throws IOException {
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
                return new Deltas();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        File file = deltaPath.toFile();
        try (FileReader reader = new FileReader(file)) {
            Deltas fileContents = new Deltas();
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                //do something with line
                fileContents.addCmd(line);
            }
//            CharBuffer buf = CharBuffer.allocate((int) file.length());      // Error if game exceeds maximum num bytes?
//            int numRead = reader.read(buf);
//            String json = buf.toString();
//            Type type = new TypeToken<List<Command>>() {}.getType();
//            return gson.fromJson(fileContents, type);
            return fileContents;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void clearDeltas(String gameid) throws Exception {
        Deltas deltas;
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
            deltas = new Deltas();
            if (deltaPath != null) {
                Files.delete(deltaPath);
            }
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

    @Override
    public void deleteGame(String gameid) throws Exception {
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
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        if (gamePath != null) {
            File file = gamePath.toFile();
            file.delete();
        }

        dir = Paths.get(dblocation, "deltas");
        Path deltaPath = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path path: files) {
                String fileName = path.getFileName().toString();
                if (fileName.equals(gameid)) {
                    gamePath = path;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        if (gamePath != null) {
            File file = gamePath.toFile();
            file.delete();
        }
    }

    @Override
    public int getDeltaCount(String gameid) throws Exception {
        Deltas deltas = getDeltas(gameid);
        return deltas.size();
    }
}
