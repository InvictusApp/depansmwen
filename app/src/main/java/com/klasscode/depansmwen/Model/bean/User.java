package com.klasscode.depansmwen.Model.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int id;
    private String username;
    private String pseudo;
    private String email;
    private String password;
    private Date createAt;

    public User(){}
    public User(int id, String username, String pseudo, String email,String password, Date createAt) {
        this.id = id;
        this.username = username;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.createAt = createAt;
    }

    public User(String username, String pseudo, String email,String password, Date createAt) {
        this.username = username;
        this.pseudo = pseudo;
        this.email= email;
        this.password = password;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
