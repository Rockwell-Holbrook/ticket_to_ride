package model;

public class User {
    private String userName;
    private String password;

    User(String userName, String password) throws Exception {
        validateUserName();
        validatePassword();

        this.userName = userName;
        this.password = password;
    }

    public void validateUserName() throws Exception {
        if(this.userName == null || this.userName.equals("") || this.userName.trim().equals("")) {
            throw new Exception("Invalid UserName!!");
        }
    }

    public void validatePassword() throws Exception {
        if(this.password == null || this.password.equals("") || this.password.trim().equals("")) {
            throw new Exception("Invalid Password!!");
        }
    }
}
