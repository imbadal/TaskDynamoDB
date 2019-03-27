package com.example.taskdynamodb;

import android.content.Context;

public class Database {
    private static Database instance;

    public static synchronized Database getInstance(Context context) {

        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }



}
