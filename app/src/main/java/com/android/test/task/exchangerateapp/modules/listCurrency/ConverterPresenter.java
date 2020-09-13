package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;

import java.util.Locale;

public class ConverterPresenter implements IConverterModuleContract.IConverterPresenter {
    private final IConverterModuleContract.IConverterView view;
    private Currency currency;

    public ConverterPresenter(IConverterModuleContract.IConverterView view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void saveCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public void onItemSelected(Currency currency) {
        saveCurrency(currency);
        setValueInFields(currency.charCode, "", "");
    }

    private void setValueInFields(String nameCurrency, String initialValue, String countedValue) {
        view.setNameCurrency(nameCurrency);
        view.setInitialValue(initialValue);
        view.setCountedValue(countedValue);
    }

    @Override
    public void onSwipeRefresh() {
        setValueInFields("Выберите валюту", "", "");
    }

    @Override
    public void onClick(String valueEditText) {
        if (!valueEditText.equals("")) {
            long rublesCount = Long.parseLong(valueEditText);
            double converterValue = rublesCount / Double.parseDouble(currency.value);
            view.setCountedValue(String.format(Locale.getDefault(), "%.4f", converterValue));
        }
    }
}
