package com.android.test.task.exchangerateapp.repository.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.model.modelDb.UpdateDate;

@Database(entities = {UpdateDate.class, Currency.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ICurrencyDao currencyDao();
}
