package ua.regin.gif.ui.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


import ua.regin.gif.R;
import ua.regin.gif.api.entity.MediaObject;
import ua.regin.gif.manager.IGifManager;
import ua.regin.gif.manager.impl.GifManager;
import ua.regin.gif.ui.BaseFragment;
import ua.regin.gif.ui.main.adapter.GifAdapter;

@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment {

    @Bean(GifManager.class)
    protected IGifManager gifManager;

    @ViewById
    protected RecyclerView recyclerView;

    private GifAdapter gifAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gifAdapter = new GifAdapter(getContext());
    }

    @AfterViews
    protected void afterViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gifAdapter);

        gifManager.loadTrendingGifList().compose(bindToLifecycle()).subscribe(this::updateData);
    }

    private void updateData(MediaObject mediaObject) {
        gifAdapter.setDataList(mediaObject.getDataList());
    }
}
