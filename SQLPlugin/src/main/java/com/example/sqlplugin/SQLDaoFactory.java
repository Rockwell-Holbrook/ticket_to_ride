package com.example.sqlplugin;

import com.example.shared.interfaces.IDaoFactory;

public class SQLDaoFactory implements IDaoFactory {

    public SQLDaoFactory(){

    }

    public SQLGameDao createGameDao(){
        return new SQLGameDao();
    }

    public SQLUserDao createUserDao(){
        return new SQLUserDao();
    }

}
