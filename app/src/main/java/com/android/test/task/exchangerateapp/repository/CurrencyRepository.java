package com.android.test.task.exchangerateapp.repository;

import android.util.Log;

import com.android.test.task.exchangerateapp.repository.api.ILoadData;
import com.android.test.task.exchangerateapp.repository.api.LoaderCurrency;
import com.android.test.task.exchangerateapp.repository.db.ICurrencyDataSource;

import org.json.JSONObject;

public class CurrencyRepository implements ICurrencyDataSource {
    private final ICurrencyDataSource currencyDataSource;

    public CurrencyRepository(ICurrencyDataSource currencyDataSource) {
        this.currencyDataSource = currencyDataSource;
    }

    @Override
    public void obtainCurrency(IObtainCurrencyCallback callback) {
        if (isEmpty()) {
            ILoadData loadData = new LoaderCurrency();
            loadData.loadData(new ILoadData.ILoadDataCallback() {
                @Override
                public void didLoad(JSONObject jsonObject) {
                    writingToTheDatabase(jsonObject);
                    currencyDataSource.obtainCurrency(callback);
                }

                @Override
                public void didFailLoad(String message) {
                    Log.e("LOAD_CURRENCY", message);

                }
            });
        }
        currencyDataSource.obtainCurrency(callback);
    }

    @Override
    public boolean isEmpty() {
        return currencyDataSource.isEmpty();
    }

    @Override
    public void writingToTheDatabase(JSONObject jsonObject) {
        currencyDataSource.writingToTheDatabase(jsonObject);
    }
}
