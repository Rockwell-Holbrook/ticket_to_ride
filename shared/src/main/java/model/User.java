package model;

public class User {
    private String userName;
    private String password;

    User(String userName, String password) throws Exception {
        this.userName = userName;
        this.password = password;
        validateUserName();
        validatePassword();
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
}
