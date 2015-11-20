package ua.regin.gif.ui;

import android.content.Context;
import android.support.v7.widget.Toolbar;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.androidannotations.annotations.EActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return getApplicationContext();
    }

    public void setToolbar(Toolbar toolbar, String title) {

    }
}
