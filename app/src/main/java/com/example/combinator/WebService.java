package com.example.combinator;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WebService {
    @GET("/integers")
    Call<String> listIntegers(@QueryMap Map<String, String> options);
}
