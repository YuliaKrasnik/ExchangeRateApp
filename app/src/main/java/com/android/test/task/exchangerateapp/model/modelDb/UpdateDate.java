package com.android.test.task.exchangerateapp.model.modelDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UpdateDate {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String date;
}
