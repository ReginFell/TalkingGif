package ua.regin.gif.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ua.regin.gif.ui.gif.GifFragment_;

public class PageAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_COUNT = 3;

    private static final String SEARCH_CATS = "cats";
    private static final String SEARCH_FUNNY = "funny";

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GifFragment_.builder().build();
            case 1:
                return GifFragment_.builder().search(SEARCH_CATS).build();
            case 2:
                return GifFragment_.builder().search(SEARCH_FUNNY).build();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}