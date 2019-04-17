package com.example.shared.commands;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Deltas {
    private ArrayList<String> list;
    private Gson gson;
    public Deltas(){
        gson = new Gson();
        list = new ArrayList<>();
    }

    public List<Command> getList() {
        List<Command> newList = new ArrayList<>();
        for (String s : list){
            newList.add(gson.fromJson(s, Command.class));
        }
        return newList;
    }

    public void addCmd(String cmd){
        list.add(cmd);
    }
    public int size(){
        return list.size();
    }
}
