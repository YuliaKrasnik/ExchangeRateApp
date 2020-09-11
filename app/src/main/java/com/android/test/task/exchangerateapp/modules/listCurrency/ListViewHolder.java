package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test.task.exchangerateapp.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView charCodeCurrency;
    private TextView nameCurrency;
    private TextView valueCurrency;
    private TextView previousValueCurrency;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        charCodeCurrency = itemView.findViewById(R.id.charCodeCurrency);
        nameCurrency = itemView.findViewById(R.id.nameCurrency);
        valueCurrency = itemView.findViewById(R.id.valueCurrency);
        previousValueCurrency = itemView.findViewById(R.id.previousValueCurrency);
    }

    public TextView getCharCodeCurrency() {
        return charCodeCurrency;
    }

    public TextView getNameCurrency() {
        return nameCurrency;
    }

    public TextView getValueCurrency() {
        return valueCurrency;
    }

    public TextView getPreviousValueCurrency() {
        return previousValueCurrency;
    }
}
