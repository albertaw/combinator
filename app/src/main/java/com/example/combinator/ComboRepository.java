package com.example.combinator;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ComboRepository {
    private Retrofit retrofit;
    private WebService service;
    private String BASE_URL = "https://www.random.org";

    public ComboRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(WebService.class);
    }

    public void listIntegers(Map<String, String> options, Callback<String> callback) {
        Call<String> call = service.listIntegers(options);
        call.enqueue(callback);
    }
}
