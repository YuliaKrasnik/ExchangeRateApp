package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.os.Bundle;

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
        setValueInAllFields(currency.charCode, "", "");
    }

    private void setValueInAllFields(String nameCurrency, String initialValue, String countedValue) {
        view.setNameCurrency(nameCurrency);
        view.setInitialValue(initialValue);
        view.setCountedValue(countedValue);
    }

    @Override
    public void onSwipeRefresh() {
        setValueInAllFields("Выберите валюту", "", "");
    }

    @Override
    public void onClick(String valueEditText) {
        if (!valueEditText.equals("") && currency!=null) {
            view.setCountedValue(String.format(Locale.getDefault(), "%.4f",  countConverterValue(valueEditText)));
        }
    }

    private double countConverterValue(String valueEditText) {
        long rublesCount = Long.parseLong(valueEditText);
        return rublesCount / Double.parseDouble(currency.value);
    }

    @Override
    public Bundle onSaveInstanceState(Bundle outState) {
        if (currency!= null) {
            outState.putString("charCode", currency.charCode);
            outState.putString("value", currency.value);
        }
        return outState;
    }

    @Override
    public void recoverData(Bundle savedInstanceState) {
        currency = new Currency();
        currency.charCode = savedInstanceState.getString("charCode");
        currency.value = savedInstanceState.getString("value");
    }

    @Override
    public void addInfoAboutCurrency(String valueEditText) {
        view.setNameCurrency(currency.charCode);
        view.setCountedValue(String.format(Locale.getDefault(), "%.4f",  countConverterValue(valueEditText)));
    }
}
