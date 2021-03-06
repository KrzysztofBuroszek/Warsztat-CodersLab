package pl.coderslab.entity;

import pl.coderslab.Warsztat_1.Funcions;

public class User extends Funcions {
    private  int id = 0;
    private  String userName = "";
    private  String email = "";
    private  String password = "";

    public User() {
    }

    public User(String email, String userName, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String email, String userName, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

//    }

    @Override
    public String toString() {
        return "User " + id + " " + userName + " " + email;
    }
}