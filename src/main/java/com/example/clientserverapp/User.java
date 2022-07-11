package com.example.clientserverapp;

public class User {
    private int id;
    private String login;
    private String password;
    private String access_rights;
    private String study_group;

    public User(String login, String password, String study_group){
        this.login=login;
        this.study_group=study_group;
        this.password=password;
    }

    public User() {}

    public String getStudy_group() {
        return study_group;
    }

    public void setStudy_group(String study_group) {
        this.study_group = study_group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAccess_rights() {
        return access_rights;
    }

    public void setAccess_rights(String access_rights) {
        this.access_rights = access_rights;
    }
}
