package com.example.memoriesmap.model;

public class User {
    private String name;
    private String email;
    private String password;
    private Note[] notes;

    public User(String name, String email, String password, Note[] notes) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }
}
