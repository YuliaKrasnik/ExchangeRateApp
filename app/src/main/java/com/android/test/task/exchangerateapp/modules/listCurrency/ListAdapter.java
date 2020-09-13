package com.android.test.task.exchangerateapp.modules.listCurrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test.task.exchangerateapp.R;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private final List<Currency> currencyList;
    private IListClickListener iListClickListener;

    public ListAdapter(List<Currency> currencyList, IListClickListener iListClickListener) {
        this.currencyList = currencyList;
        this.iListClickListener = iListClickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        final ListViewHolder listViewHolder = new ListViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = listViewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    iListClickListener.onItemClicked(getCurrencyList().get(position));
                }
            }
        });
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.getCharCodeCurrency().setText(currencyList.get(position).charCode);
        holder.getNameCurrency().setText(currencyList.get(position).name);
        holder.getValueCurrency().setText(currencyList.get(position).value);
        holder.getPreviousValueCurrency().setText(currencyList.get(position).previous);
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
