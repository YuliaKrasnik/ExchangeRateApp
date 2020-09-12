package com.android.test.task.exchangerateapp.model.modelDb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Currency {
    @PrimaryKey
    @NonNull
    public String id;
    public String numCode;
    public String charCode;
    public String nominal;
    public String name;
    public String value;
    public String previous;
}
