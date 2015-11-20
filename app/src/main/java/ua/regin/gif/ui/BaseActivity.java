package ua.regin.gif.ui;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.androidannotations.annotations.EActivity;

import ua.regin.gif.R;

public abstract class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return getApplicationContext();
    }

    public abstract void setToolbar(Toolbar toolbar, @StringRes int titleId);
}
