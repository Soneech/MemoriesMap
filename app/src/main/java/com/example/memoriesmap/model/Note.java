package com.example.memoriesmap.model;

import com.google.type.DateTime;

public class Note {
    private String title;
    private String text;
    private DateTime dateTime;
    private int[] coordinates;

    public Note(String title, String text, DateTime dateTime, int[] coordinates) {
        this.title = title;
        this.text = text;
        this.dateTime = dateTime;
        this.coordinates = coordinates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }
}
