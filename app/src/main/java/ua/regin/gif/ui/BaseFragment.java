package ua.regin.gif.ui;

import android.app.Activity;
import android.content.Context;

import com.trello.rxlifecycle.components.support.RxFragment;

import org.androidannotations.annotations.EFragment;

public class BaseFragment extends RxFragment {

    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    public BaseActivity getBaseActivity() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            return (BaseActivity) activity;
        }
        throw new RuntimeException("Base Fragment must be hosted in Base Activity");
    }
}
