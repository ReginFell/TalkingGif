package ua.regin.gif.ui.gif;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import ua.regin.gif.R;
import ua.regin.gif.api.entity.MediaObject;
import ua.regin.gif.manager.IGifManager;
import ua.regin.gif.manager.impl.GifManager;
import ua.regin.gif.preferance.PreferanceHelper_;
import ua.regin.gif.ui.BaseFragment;
import ua.regin.gif.ui.gif.adapter.GifAdapter;

@EFragment(R.layout.fragment_gif)
public class GifFragment extends BaseFragment {

    @Pref
    protected PreferanceHelper_ preferanceHelper;

    @FragmentArg
    protected String search;

    @Bean(GifManager.class)
    protected IGifManager gifManager;

    @ViewById
    protected RecyclerView recyclerView;

    private GifAdapter adapter;
    private GridLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new GifAdapter(getContext());
    }

    @AfterViews
    protected void afterViews() {
        adapter.setAutoplay(preferanceHelper.autoplay().get());
        layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getFocusedState(position)) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (search == null) {
            gifManager.loadTrendingGifList().compose(bindToLifecycle()).subscribe(this::updateData);
        } else {
            gifManager.searchGifs(search).subscribe(this::updateData);
        }
    }

    private void updateData(MediaObject mediaObject) {
        adapter.setDataList(mediaObject.getDataList());
    }
}
