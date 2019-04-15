package com.example.shared.interfaces;

public interface IDaoFactory {
    public IGameDao createGameDao();
    public IUserDao createUserDao();
}
