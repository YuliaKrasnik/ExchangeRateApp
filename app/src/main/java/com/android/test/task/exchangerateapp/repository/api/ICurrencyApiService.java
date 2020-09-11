package com.android.test.task.exchangerateapp.repository.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ICurrencyApiService {
    @GET("/daily_json.js")
    Call<ResponseBody> getResponse();
}
