package com.example.shared.commands;

import java.util.ArrayList;
import java.util.List;

public class Deltas {
    private ArrayList<String> list;
    public Deltas(){
        list = new ArrayList<>();
    }

    public List<Command> getList() {
        List<Command> newList = new ArrayList<>();
        for (String s : list){
            newList.add(new Command(s));
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
