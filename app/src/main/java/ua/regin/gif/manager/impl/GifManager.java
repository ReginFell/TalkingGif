package ua.regin.gif.manager.impl;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.regin.gif.api.IApiManager;
import ua.regin.gif.api.entity.MediaObject;
import ua.regin.gif.api.impl.ApiManager;
import ua.regin.gif.manager.IGifManager;

@EBean(scope = EBean.Scope.Singleton)
public class GifManager implements IGifManager {

    private static final int LIMIT = 100;

    @Bean(ApiManager.class)
    protected IApiManager apiManager;

    private API api;

    @AfterInject
    protected void afterInject() {
        api = apiManager.getRetrofitAdapter().create(API.class);
    }

    @Override
    public Observable<MediaObject> loadRandomGif() {
        return api.loadRandomGif().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MediaObject> loadTrendingGifList() {
        return api.loadTrendingGifList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MediaObject> searchGifs(String search) {
        return api.searchGifs(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable addSchedulers(Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private interface API {

        @GET("/v1/gifs/random?api_key=" + ApiManager.API_KEY)
        Observable<MediaObject> loadRandomGif();

        @GET("/v1/gifs/trending?limit=" + LIMIT + "&api_key=" + ApiManager.API_KEY)
        Observable<MediaObject> loadTrendingGifList();

        @GET("/v1/gifs/search?limit=" + LIMIT + "&api_key=" + ApiManager.API_KEY)
        Observable<MediaObject> searchGifs(@Query("q") String search);
    }
}