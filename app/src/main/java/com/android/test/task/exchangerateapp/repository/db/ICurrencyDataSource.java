package com.android.test.task.exchangerateapp.repository.db;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;

import org.json.JSONObject;

import java.util.List;

public interface ICurrencyDataSource {
    void obtainCurrency(final IObtainCurrencyCallback callback);

    boolean isEmpty();

    void writingToTheDatabase(JSONObject jsonObject);

    interface IObtainCurrencyCallback {
        void didObtain(final List<Currency> currencies);

        void didFailObtain(final int errorStatusCode);
    }
}
