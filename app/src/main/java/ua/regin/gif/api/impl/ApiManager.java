package ua.regin.gif.api.impl;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.EBean;

import java.nio.charset.Charset;

import retrofit.Retrofit;
import ua.regin.gif.api.IApiManager;

@EBean(scope = EBean.Scope.Singleton)
public class ApiManager implements IApiManager{

    private static final String BASE_URL = "https://giphy.p.mashape.com/v1/";

    private final Context context;
    private final Retrofit retrofit;

    public ApiManager(Context context) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL).build();
    }

    public Retrofit getRestAdapter() {
        return retrofit;
    }
}
