package com.example.memoriesmap.model;

import com.yandex.mapkit.geometry.Point;

public class Memory {
    private String title;
    private String date;
    private String text;
    private Point point;
    private String userKey;

    private String pointString;

    public Memory() {

    }

    public Memory(String title, String date, String text, Point point, String userKey, String pointString) {
        this.title = title;
        this.date = date;
        this.text = text;
        this.point = point;
        this.userKey = userKey;
        this.pointString = pointString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getPointString() {
        return pointString;
    }

    public void setPointString(String pointString) {
        this.pointString = pointString;
    }
}
