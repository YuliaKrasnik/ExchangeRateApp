package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.modules.common.IBasePresenter;
import com.android.test.task.exchangerateapp.modules.common.IBaseView;


public interface IConverterModuleContract {
    interface IConverterView extends IBaseView<IConverterModuleContract.IConverterPresenter> {
        void onItemSelected(Currency currency);

        void onSwipeRefresh();

        void setCountedValue(String value);

        void setNameCurrency(String name);

        void setInitialValue(String value);
    }

    interface IConverterPresenter extends IBasePresenter {
        void onItemSelected(Currency currency);

        void onSwipeRefresh();

        void onClick(String valueEditText);
    }
}
