package com.example.flatfileplugin;

import com.example.shared.interfaces.IUserDao;
import com.example.shared.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FFUserDao implements IUserDao {
    private final String dblocation = "C:\\Users\\taylo\\StudioProjects\\ticket_to_ride\\FFDatabase";
    private Gson gson = new Gson();

    @Override
    public void registerUser(User user) throws IOException {
        List<User> users = getUsers();
        users.add(user);
        putUsers(users);
    }

    @Override
    public User getUser(String username) throws IOException {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void clear() {

    }

    private List<User> getUsers() throws IOException {
        File userFile = new File(dblocation, "users");
        try (FileReader reader = new FileReader(userFile)) {
            CharBuffer buf = CharBuffer.allocate((int) userFile.length());      // Error if game exceeds maximum num bytes?
            int numRead = reader.read(buf);
            String json = buf.toString();
            Type type = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void putUsers(List<User> users) throws IOException {
        File userFile = new File(dblocation, "users");
        userFile.delete();
        Path usersPath = Files.createFile(Paths.get(dblocation, "users"));
        try (FileWriter writer = new FileWriter(usersPath.toFile())) {
            writer.write(gson.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
