package com.android.test.task.exchangerateapp.repository.api;

import retrofit2.Retrofit;

public class NetworkService {
    private static NetworkService instance;
    private static final String BASE_URL = "https://www.cbr-xml-daily.ru";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
    }

    static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    ICurrencyApiService getCurrencyApi() {
        return retrofit.create(ICurrencyApiService.class);
    }
}
