package com.android.test.task.exchangerateapp.useCase.currency;

import android.util.Log;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.repository.CurrencyRepository;
import com.android.test.task.exchangerateapp.repository.db.ICurrencyDataSource;
import com.android.test.task.exchangerateapp.useCase.common.UseCase;

import java.util.List;
public class RefreshCurrencyUseCase extends UseCase<RefreshCurrencyUseCase.RequestValues, RefreshCurrencyUseCase.ResponseValues> {
    private final CurrencyRepository currencyRepository;

    public RefreshCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    protected void execute(RequestValues requestParameters) {
        currencyRepository.refreshCurrency(new ICurrencyDataSource.IObtainCurrencyCallback() {
            @Override
            public void didObtain(List<Currency> currencies) {
                final ResponseValues responseValues = new ResponseValues(currencies);
                getCallback().onSuccess(responseValues);
            }

            @Override
            public void didFailObtain(int errorStatusCode) {
                Log.e("REFRESH_CURRENCY", String.valueOf(errorStatusCode));
            }
        });
    }

    public static final class RequestValues implements UseCase.IRequestValues {
    }

    public static final class ResponseValues implements UseCase.IResponseValues {
        private final List<Currency> currencyList;

        public ResponseValues(List<Currency> currencyList) {
            this.currencyList = currencyList;
        }

        public List<Currency> getCurrencyList() {
            return currencyList;
        }
    }
}

