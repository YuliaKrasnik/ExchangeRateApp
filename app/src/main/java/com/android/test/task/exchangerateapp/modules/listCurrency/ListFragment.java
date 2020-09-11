package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.test.task.exchangerateapp.R;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;

import java.util.List;
import java.util.Objects;

public class ListFragment extends Fragment implements IListModuleContract.IListView {
    private IListModuleContract.IListPresenter presenter;
    private ListAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private boolean isFirstInitialized = true;

    @Override
    public void setPresenter(IListModuleContract.IListPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
    //    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        Drawable divider = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        if (divider != null) {
            itemDecoration.setDrawable(divider);
        }
        recyclerView.addItemDecoration(itemDecoration);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstInitialized) {
            presenter.onResume();
            isFirstInitialized = false;
        }
    }

    @Override
    public void showCurrencies(List<Currency> currencies) {
        adapter = new ListAdapter(currencies);
        recyclerView.setAdapter(adapter);
    }
}
