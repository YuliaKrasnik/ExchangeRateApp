package com.android.test.task.exchangerateapp.repository.db;

import com.android.test.task.exchangerateapp.App;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.model.modelDb.UpdateDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class CacheCurrencyDataSource implements ICurrencyDataSource {
    private AppDatabase db = App.getInstance().getDatabase();
    private ICurrencyDao currencyDao = db.currencyDao();

    @Override
    public void obtainCurrency(IObtainCurrencyCallback callback) {

    }

    public void writingToTheDatabase(JSONObject jsonObject) {
        try {
            UpdateDate updateDate = new UpdateDate();
            updateDate.date = jsonObject.getString("Date");
            currencyDao.insertDate(updateDate);

            JSONObject valute = jsonObject.getJSONObject("Valute");
            Iterator<String> keys = valute.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonObjectValute = (JSONObject) valute.get(key);

                Currency currency = new Currency();
                currency.id = jsonObjectValute.getString("ID");
                currency.numCode = jsonObjectValute.getString("NumCode");
                currency.charCode = jsonObjectValute.getString("CharCode");
                currency.nominal = jsonObjectValute.getString("Nominal");
                currency.name = jsonObjectValute.getString("Name");
                currency.value = jsonObjectValute.getString("Value");
                currency.previous = jsonObjectValute.getString("Previous");

                currencyDao.insertCurrency(currency);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
