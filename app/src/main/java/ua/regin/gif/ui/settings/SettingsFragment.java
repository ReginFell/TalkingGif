package ua.regin.gif.ui.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ua.regin.gif.R;
import ua.regin.gif.ui.BaseFragment;

@EFragment(R.layout.fragment_settings)
public class SettingsFragment extends BaseFragment {

    @ViewById
    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment fragment = SettingsFragment_.InnerSettingsFragment_.builder().build();
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.settings_container, fragment)
                    .commit();
        }
    }

    @AfterViews
    protected void afterViews() {
        getBaseActivity().setToolbar(toolbar, R.string.fragment_settings_title);
    }

    @EFragment
    public static class InnerSettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {

        }

        @Override
        public void onDisplayPreferenceDialog(Preference preference) {
            super.onDisplayPreferenceDialog(preference);
        }

    }
}
