package com.android.test.task.exchangerateapp.repository.db;

import com.android.test.task.exchangerateapp.App;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.model.modelDb.UpdateDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CacheCurrencyDataSource implements ICurrencyDataSource {
    private AppDatabase db = App.getInstance().getDatabase();
    private ICurrencyDao currencyDao = db.currencyDao();

    @Override
    public void obtainCurrency(IObtainCurrencyCallback callback) {
        callback.didObtain(getCurrencyListFromDb());
    }

    @Override
    public boolean isEmpty() {
        boolean flag;
        Currency currency = currencyDao.getAnyCurrency();
        flag = currency == null;
        return flag;
    }

    @Override
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

    @Override
    public void refreshCurrency(IObtainCurrencyCallback callback) {
        callback.didObtain(getCurrencyListFromDb());
    }

    private List<Currency> getCurrencyListFromDb() {
        return currencyDao.getCurrency();
    }

    @Override
    public boolean compareUpdateDates(JSONObject jsonObject) {
        String newDate = null;
        try {
            newDate = jsonObject.getString("Date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String oldDate = currencyDao.getUpdateDate();

        return newDate == null || getDateFromString(newDate).getTime() == getDateFromString(oldDate).getTime();
    }

    @Override
    public void deleteDataFromDb() {
        currencyDao.deleteCurrency();
        currencyDao.deleteDate();
    }

    private Date getDateFromString(String dateStr) {
        Date formattedDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            formattedDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
