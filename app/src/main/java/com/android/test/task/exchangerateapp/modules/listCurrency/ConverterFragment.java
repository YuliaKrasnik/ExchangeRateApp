package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.test.task.exchangerateapp.R;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;

public class ConverterFragment extends Fragment implements IConverterModuleContract.IConverterView {
    IConverterModuleContract.IConverterPresenter presenter;
    private EditText initialValue;
    private TextView selectedNameCurrency;
    private TextView countedValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        initialValue = view.findViewById(R.id.initialValue);
        selectedNameCurrency = view.findViewById(R.id.selectedNameCurrency);
        countedValue = view.findViewById(R.id.countedValue);
        ImageButton btnConverter = view.findViewById(R.id.btnConverter);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick(initialValue.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void setPresenter(IConverterModuleContract.IConverterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemSelected(Currency currency) {
        presenter.onItemSelected(currency);
    }

    @Override
    public void onSwipeRefresh() {
        presenter.onSwipeRefresh();
    }

    @Override
    public void setCountedValue(String value) {
        countedValue.setText(value);
    }

    @Override
    public void setNameCurrency(String name) {
        selectedNameCurrency.setText(name);
    }

    @Override
    public void setInitialValue(String value) {
        initialValue.setText(value);
    }

}
