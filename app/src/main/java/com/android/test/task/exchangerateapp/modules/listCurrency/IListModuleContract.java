package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.modules.common.IBasePresenter;
import com.android.test.task.exchangerateapp.modules.common.IBaseView;

import java.util.List;

public interface IListModuleContract {
    interface IListView extends IBaseView<IListPresenter> {

        void showCurrencies(List<Currency> currencies);

        void setRefreshing(boolean flag);
    }

    interface IListPresenter extends IBasePresenter {

        void onResume();

        void onRefresh();
    }
}
