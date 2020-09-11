package com.android.test.task.exchangerateapp.repository.db;

import androidx.room.Dao;
import androidx.room.Insert;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.model.modelDb.UpdateDate;

@Dao
public interface ICurrencyDao {
    @Insert
    void insertDate(UpdateDate updateDate);

    @Insert
    void insertCurrency(Currency currency);
}
