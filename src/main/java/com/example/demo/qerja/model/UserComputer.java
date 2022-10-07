package com.example.demo.qerja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserComputer {

    private @Id
    @GeneratedValue Long idUser;
    private String username;
    private String roleUser;
    private String password;

    public UserComputer() {}

    public UserComputer(String username, String role, String password) {
        this.username = username;
        this.roleUser = role;
        this.password = password;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRoleUser() {
        return this.roleUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.idUser + ", userName='" + this.username + '\'' + ", role='" + this.roleUser + '\'' + '}';
    }
}