package com.android.test.task.exchangerateapp.repository.db;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;

import java.util.List;

public interface ICurrencyDataSource {
    void obtainCurrency(final IObtainCurrencyCallback callback);

    interface IObtainCurrencyCallback {
        void didObtain(final List<Currency> news);

        void didFailObtain(final int errorStatusCode);
    }
}
