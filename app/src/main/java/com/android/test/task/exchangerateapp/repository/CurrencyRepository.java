package com.android.test.task.exchangerateapp.repository;

import com.android.test.task.exchangerateapp.repository.db.ICurrencyDataSource;

public class CurrencyRepository implements ICurrencyDataSource {
    private final ICurrencyDataSource currencyDataSource;

    public CurrencyRepository(ICurrencyDataSource currencyDataSource) {
        this.currencyDataSource = currencyDataSource;
    }

    @Override
    public void obtainCurrency(IObtainCurrencyCallback callback) {

    }
}
