package com.android.test.task.exchangerateapp.repository.db;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.model.modelDb.UpdateDate;

import java.util.List;

@Dao
public interface ICurrencyDao {
    @Insert
    void insertDate(UpdateDate updateDate);

    @Insert
    void insertCurrency(Currency currency);

    @Query("DELETE FROM UpdateDate")
    void deleteDate();

    @Query("DELETE FROM Currency")
    void deleteCurrency();

    @Query("SELECT * FROM Currency LIMIT 1")
    @Nullable
    Currency getAnyCurrency();

    @Query("SELECT * FROM Currency")
    List<Currency> getCurrency();

    @Query("SELECT date FROM UpdateDate")
    String getUpdateDate();
}
