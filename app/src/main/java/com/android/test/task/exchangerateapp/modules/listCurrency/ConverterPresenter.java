package com.android.test.task.exchangerateapp.modules.listCurrency;

public class ConverterPresenter implements IConverterModuleContract.IConverterPresenter {
    private final IConverterModuleContract.IConverterView view;

    public ConverterPresenter(IConverterModuleContract.IConverterView view) {
        this.view = view;
        view.setPresenter(this);
    }
}
