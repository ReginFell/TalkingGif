package ua.regin.gif.api.impl;

import android.content.Context;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import ua.regin.gif.api.IApiManager;

@EBean(scope = EBean.Scope.Singleton)
public class ApiManager implements IApiManager {

    private static final String BASE_URL = "https://giphy.p.mashape.com";
    public static final String API_KEY = "dc6zaTOxFJmzC";

    private final Context context;
    private final Retrofit retrofit;

    public ApiManager(Context context) {
        this.context = context;

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("X-Mashape-Key", "nTQdJ01qrAmshzEei35gOKrjD31Wp1sjEJojsnOEJs8rOffcbR")
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(request);
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.interceptors().add(interceptor);

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public Retrofit getRetrofitAdapter() {
        return retrofit;
    }
}
