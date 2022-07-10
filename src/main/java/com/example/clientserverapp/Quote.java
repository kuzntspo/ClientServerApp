package com.example.clientserverapp;

import java.util.Date;

public class Quote {
    int id;
    int user_id;
    String quote;
    String surname;
    String name;
    String middle_name;
    String subject;
    Date date;

    public Quote(int id, int user_id, String quote, String surname, String name, String middle_name, String subject, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.quote = quote;
        this.surname = surname;
        this.name = name;
        this.middle_name = middle_name;
        this.subject = subject;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
