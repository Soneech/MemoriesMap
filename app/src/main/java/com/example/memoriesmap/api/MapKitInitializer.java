package com.example.memoriesmap.api;

public class MapKitInitializer {
    public static boolean isInitialize = false;

    public void setInitialize() {
        if (!isInitialize) {
            isInitialize = true;
        }
    }

    public boolean getInitialize() {
        return isInitialize;
    }
}
