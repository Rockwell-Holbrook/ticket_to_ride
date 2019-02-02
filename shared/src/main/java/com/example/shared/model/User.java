package com.example.shared.model;

public class User {
    private static User instance;
    private String userName;
    private String password;

    public static synchronized User getInstance() throws Throwable {
        if (instance == null){
            throw new Exception("Nobody is logged in");
        }
        return instance;
    }

    User(String userName, String password) throws Exception {
        this.userName = userName;
        this.password = password;
        validateUserName();
        validatePassword();
        instance = this;
    }

    public void validateUserName() throws Exception {
        if(this.userName == null || this.userName.equals("") || this.userName.equals(" ")) {
            throw new Exception("Invalid UserName!!");
        }
    }

    public void validatePassword() throws Exception {
        if(this.password == null || this.password.equals("") || this.password.equals(" ")) {
            throw new Exception("Invalid Password!!");
        }
    }

    public String getUserName() {
        return userName;
    }
}
