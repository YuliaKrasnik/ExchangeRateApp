package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.content.Context;
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

public class ListFragment extends Fragment implements IListModuleContract.IListView, IListClickListener {
    private IListModuleContract.IListPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    private boolean isFirstInitialized = true;

    @Override
    public void onItemClicked(Currency currency) {
        onFragmentInteractionListener.onItemSelected(currency);
    }

    interface OnFragmentInteractionListener {
        void onItemSelected(Currency currency);

        void onSwipeRefresh();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onFragmentInteractionListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void setPresenter(IListModuleContract.IListPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        Drawable divider = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        if (divider != null) {
            itemDecoration.setDrawable(divider);
        }
        recyclerView.addItemDecoration(itemDecoration);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this::refresh);

        return view;
    }

    private void refresh() {
        setRefreshing(true);
        onFragmentInteractionListener.onSwipeRefresh();
        presenter.onRefresh();
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
        ListAdapter adapter = new ListAdapter(currencies, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRefreshing(boolean flag) {
        swipeRefreshLayout.setRefreshing(flag);
    }
}
