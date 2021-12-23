package com.example.fpmobile;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://asl-fp-mobile.herokuapp.com/";
    private  static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (mInstance ==null){
            mInstance = new RetrofitClient();
        }
        return mInstance;

    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
