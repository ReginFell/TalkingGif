package ua.regin.gif.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import ua.regin.gif.R;
import ua.regin.gif.ui.BaseActivity;
import ua.regin.gif.ui.BaseFragment;
import ua.regin.gif.ui.main.adapter.PageAdapter;

@EFragment(R.layout.fragment_main)
@OptionsMenu(R.menu.menu_main)
public class MainFragment extends BaseFragment {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected TabLayout tabLayout;

    @ViewById
    protected ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @AfterViews
    protected void afterViews() {
        getBaseActivity().setToolbar(toolbar, R.string.fragment_gif_title);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.trending));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.cats));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.funny));

        final PageAdapter adapter = new PageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
