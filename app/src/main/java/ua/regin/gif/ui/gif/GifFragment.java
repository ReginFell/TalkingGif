package ua.regin.gif.ui.gif;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.MediaObject;
import ua.regin.gif.manager.IGifManager;
import ua.regin.gif.manager.impl.GifManager;
import ua.regin.gif.ui.BaseFragment;
import ua.regin.gif.ui.gif.adapter.GifAdapter;

@EFragment(R.layout.fragment_gif)
public class GifFragment extends BaseFragment {

    @FragmentArg
    protected String search;

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
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? layoutManager.getSpanCount() : 1;
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gifAdapter);

        if (search == null) {
            gifManager.loadTrendingGifList().compose(bindToLifecycle()).subscribe(this::updateData);
        } else {
            gifManager.searchGifs(search).subscribe(this::updateData);
        }
    }

    private void updateData(MediaObject mediaObject) {
        gifAdapter.setDataList(mediaObject.getDataList());
    }
}
