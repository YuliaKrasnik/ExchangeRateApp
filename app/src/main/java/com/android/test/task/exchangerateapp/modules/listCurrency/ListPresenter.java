package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.useCase.common.UseCase;
import com.android.test.task.exchangerateapp.useCase.common.UseCaseExecutor;
import com.android.test.task.exchangerateapp.useCase.currency.ObtainCurrencyUseCase;

import java.util.List;

public class ListPresenter implements IListModuleContract.IListPresenter {
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;
    private final ObtainCurrencyUseCase obtainCurrencyUseCase;

    public ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor, ObtainCurrencyUseCase obtainCurrencyUseCase) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.obtainCurrencyUseCase = obtainCurrencyUseCase;
        view.setPresenter(this);
    }

    @Override
    public void onResume() {
        obtainCurrency();
    }

    private void obtainCurrency() {
        final ObtainCurrencyUseCase.RequestValues requestValues = new ObtainCurrencyUseCase.RequestValues();
        useCaseExecutor.execute(obtainCurrencyUseCase, requestValues, new UseCase.IUseCaseCallback<ObtainCurrencyUseCase.ResponseValues>() {
            @Override
            public void onSuccess(ObtainCurrencyUseCase.ResponseValues response) {
                final List<Currency> currencies = response.getCurrencyList();
                view.showCurrencies(currencies);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
