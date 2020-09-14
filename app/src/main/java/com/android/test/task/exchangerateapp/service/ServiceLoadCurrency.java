package com.android.test.task.exchangerateapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.repository.CurrencyRepository;
import com.android.test.task.exchangerateapp.repository.db.CacheCurrencyDataSource;
import com.android.test.task.exchangerateapp.repository.db.ICurrencyDataSource;
import com.android.test.task.exchangerateapp.useCase.common.UseCase;
import com.android.test.task.exchangerateapp.useCase.common.UseCaseExecutor;
import com.android.test.task.exchangerateapp.useCase.currency.RefreshCurrencyUseCase;

import java.util.List;

public class ServiceLoadCurrency extends IntentService {
    private UseCaseExecutor useCaseExecutor;
    private RefreshCurrencyUseCase refreshCurrencyUseCase;

    private final static String TAG_LOG = "ALARM_SERVICE";

    public ServiceLoadCurrency() {
        super("myService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final ICurrencyDataSource currencyDataSource = new CacheCurrencyDataSource();
        final CurrencyRepository currencyRepository = new CurrencyRepository(currencyDataSource);
        useCaseExecutor = UseCaseExecutor.getInstance();
        refreshCurrencyUseCase = new RefreshCurrencyUseCase(currencyRepository);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final RefreshCurrencyUseCase.RequestValues requestValues = new RefreshCurrencyUseCase.RequestValues();
        useCaseExecutor.execute(refreshCurrencyUseCase, requestValues, new UseCase.IUseCaseCallback<RefreshCurrencyUseCase.ResponseValues>() {
            @Override
            public void onSuccess(RefreshCurrencyUseCase.ResponseValues response) {
                final List<Currency> currencies = response.getCurrencyList();
                if (currencies != null) {
                    Log.i(TAG_LOG, "load in service success, size currencies list = " + currencies.size());
                } else {
                    Log.i(TAG_LOG, "load in service success, size currencies list null (no new data)");
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.i(TAG_LOG, "load in service with error");
            }
        });
    }
}
