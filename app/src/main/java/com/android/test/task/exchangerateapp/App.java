package com.android.test.task.exchangerateapp;

import android.app.Application;

import androidx.room.Room;

import com.android.test.task.exchangerateapp.repository.db.AppDatabase;

public class App extends Application {
    public static App instance;
    private AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
}
