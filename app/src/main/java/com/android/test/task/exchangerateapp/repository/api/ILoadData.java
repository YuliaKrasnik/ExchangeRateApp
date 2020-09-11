package com.android.test.task.exchangerateapp.repository.api;

import org.json.JSONObject;

public interface ILoadData {
    void loadData(final ILoadDataCallback callback);

    interface ILoadDataCallback {
        void didLoad(final JSONObject jsonObject);

        void didFailLoad(String message);
    }
}
