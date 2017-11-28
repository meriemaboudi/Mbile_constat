package com.example.oualhaomar.insurancepi.Entities;

/**
 * Created by oualhaomar on 23/11/2017.
 */

public class User {
    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private String Email;
    private Boolean isActif;
    private Integer Online;


    public User() {
    }

    public User(Integer id, String login, String password, String firstName, String lastName, String email, Boolean isActif, Integer online) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        this.isActif = isActif;
        Online = online;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getActif() {
        return isActif;
    }

    public void setActif(Boolean actif) {
        isActif = actif;
    }

    public Integer getOnline() {
        return Online;
    }

    public void setOnline(Integer online) {
        Online = online;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Email='" + Email + '\'' +
                ", isActif=" + isActif +
                ", Online=" + Online +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
