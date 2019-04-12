package com.example.sqlplugin;

public class SQLDaoFactory {

    public SQLGameDao createGameDao(){
        return new SQLGameDao();
    }

    public SQLUserDao createUserDao(){
        return new SQLUserDao();
    }

}
