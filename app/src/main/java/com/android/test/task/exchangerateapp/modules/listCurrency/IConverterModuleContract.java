package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.modules.common.IBasePresenter;
import com.android.test.task.exchangerateapp.modules.common.IBaseView;


public interface IConverterModuleContract {
    interface IConverterView extends IBaseView<IConverterModuleContract.IConverterPresenter> {

    }

    interface IConverterPresenter extends IBasePresenter {

    }
}
