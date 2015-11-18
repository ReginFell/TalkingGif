package ua.regin.gif.ui;

import android.content.Context;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.androidannotations.annotations.EActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return getApplicationContext();
    }
}
