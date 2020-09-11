package com.android.test.task.exchangerateapp.repository.api;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaderCurrency implements ILoadData {
    @Override
    public void loadData(final ILoadDataCallback callback) {
        NetworkService.getInstance().getCurrencyApi().getResponse().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            String body = response.body().string();
                            JSONObject jsonObject = new JSONObject(body);
                            callback.didLoad(jsonObject);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    callback.didFailLoad("Request Error :: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                if (call.isCanceled()) {
                    callback.didFailLoad("Call was cancelled forcefully");
                } else {
                    callback.didFailLoad("Network Error :: " + t.getLocalizedMessage());
                }
            }
        });
    }
}
