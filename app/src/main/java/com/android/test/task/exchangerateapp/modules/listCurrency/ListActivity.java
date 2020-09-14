package com.android.test.task.exchangerateapp.modules.listCurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.android.test.task.exchangerateapp.R;
import com.android.test.task.exchangerateapp.model.modelDb.Currency;
import com.android.test.task.exchangerateapp.receiver.AlarmReceiver;
import com.android.test.task.exchangerateapp.repository.CurrencyRepository;
import com.android.test.task.exchangerateapp.repository.db.CacheCurrencyDataSource;
import com.android.test.task.exchangerateapp.repository.db.ICurrencyDataSource;
import com.android.test.task.exchangerateapp.useCase.common.UseCaseExecutor;
import com.android.test.task.exchangerateapp.useCase.currency.ObtainCurrencyUseCase;
import com.android.test.task.exchangerateapp.useCase.currency.RefreshCurrencyUseCase;

public class ListActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {
    IListModuleContract.IListView view;
    IConverterModuleContract.IConverterView converterView;
    Fragment fragment;
    ConverterFragment converterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (null == fragment) {
            fragment = new ListFragment();
            fragmentTransaction
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        view = (IListModuleContract.IListView) fragment;


        final ICurrencyDataSource currencyDataSource = new CacheCurrencyDataSource();
        final CurrencyRepository currencyRepository = new CurrencyRepository(currencyDataSource);
        final UseCaseExecutor useCaseExecutor = UseCaseExecutor.getInstance();
        final ObtainCurrencyUseCase obtainCurrencyUseCase = new ObtainCurrencyUseCase(currencyRepository);
        final RefreshCurrencyUseCase refreshCurrencyUseCase = new RefreshCurrencyUseCase(currencyRepository);
        final IListModuleContract.IListPresenter presenter = new ListPresenter(view, useCaseExecutor, obtainCurrencyUseCase, refreshCurrencyUseCase);

        converterFragment = (ConverterFragment) getSupportFragmentManager().findFragmentById(R.id.converterFragment);
        converterView = converterFragment;

        if (converterView != null) {
            final IConverterModuleContract.IConverterPresenter converterPresenter = new ConverterPresenter(converterView);
        }

        restartPeriodicLoad();

    }

    private void restartPeriodicLoad() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY * 2, pendingIntent);
    }

    @Override
    public void onItemSelected(Currency currency) {
        converterView.onItemSelected(currency);
    }

    @Override
    public void onSwipeRefresh() {
        converterView.onSwipeRefresh();
    }
}