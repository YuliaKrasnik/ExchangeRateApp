package com.android.test.task.exchangerateapp.modules.listCurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.test.task.exchangerateapp.R;

public class ListActivity extends AppCompatActivity {
    IListModuleContract.IListView view;
    Fragment fragment;
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

    }
}