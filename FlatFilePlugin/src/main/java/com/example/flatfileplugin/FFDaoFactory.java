package com.example.flatfileplugin;

import com.example.shared.interfaces.IDaoFactory;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.interfaces.IUserDao;


public class FFDaoFactory implements IDaoFactory {

    public FFDaoFactory() {}

    @Override
    public IGameDao createGameDao() {
        return new FFGameDao();
    }

    @Override
    public IUserDao createUserDao() {
        return new FFUserDao();
    }
}
