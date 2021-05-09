package com.example.combinator;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComboViewModel extends ViewModel {
    private MutableLiveData<String> combo;
    private ComboRepository repository;
    private String TAG = "ComboViewModel";

    public ComboViewModel() {
        repository = new ComboRepository();
    }

    public LiveData<String> getCombo() {
        if (combo == null) {
            combo = new MutableLiveData<>();
            loadCombo();
        }
        return combo;
    }

    public void loadCombo() {
        Map<String, String> options = new HashMap<>();
        options.put("num", "4");
        options.put("min", "0");
        options.put("max", "7");
        options.put("col", "1");
        options.put("base", "10");
        options.put("format", "plain");
        options.put("rnd", "new");
        repository.listIntegers(options, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    combo.setValue(response.body());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e(TAG, errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
