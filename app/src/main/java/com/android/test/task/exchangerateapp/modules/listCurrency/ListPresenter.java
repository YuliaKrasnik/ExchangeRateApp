package com.android.test.task.exchangerateapp.modules.listCurrency;

import com.android.test.task.exchangerateapp.useCase.common.UseCaseExecutor;

public class ListPresenter implements IListModuleContract.IListPresenter {
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;

    public ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;

        view.setPresenter(this);
    }
}
