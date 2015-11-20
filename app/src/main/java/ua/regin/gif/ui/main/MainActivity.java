package ua.regin.gif.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ua.regin.gif.R;
import ua.regin.gif.ui.BaseActivity;
import ua.regin.gif.ui.settings.SettingsFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    protected NavigationView navigationView;

    @ViewById
    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            switchFragment(MainFragment_.builder().build());
        }
    }

    @AfterViews
    protected void afterViews() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);

            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.drawer_gif:
                    fragment = MainFragment_.builder().build();
                    break;
                case R.id.drawer_sticker:
                    fragment = MainFragment_.builder().build(); //TODO REPLACE WITH S
                    break;
                case R.id.drawer_settings:
                    fragment = SettingsFragment_.builder().build();
                    break;
                default:
                    throw new RuntimeException("Unknown fragment type");
            }

            switchFragment(fragment);
            drawerLayout.closeDrawers();
            return true;
        });

    }

    @Override
    public void setToolbar(Toolbar toolbar, int titleId) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(ignored -> drawerLayout.openDrawer(GravityCompat.START));

        if (actionBar != null) {
            actionBar.setTitle(titleId);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
