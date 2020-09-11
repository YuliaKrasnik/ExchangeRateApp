package com.android.test.task.exchangerateapp.repository.db;

import com.android.test.task.exchangerateapp.App;

import java.util.ArrayList;

public class CacheCurrencyDataSource implements ICurrencyDataSource {
    private AppDatabase db = App.getInstance().getDatabase();
    private ICurrencyDao currencyDao = db.currencyDao();

    @Override
    public void obtainCurrency(IObtainCurrencyCallback callback) {

    }
}
