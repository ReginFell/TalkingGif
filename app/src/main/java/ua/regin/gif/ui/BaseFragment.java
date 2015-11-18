package ua.regin.gif.ui;

import android.content.Context;

import com.trello.rxlifecycle.components.support.RxFragment;

import org.androidannotations.annotations.EFragment;

public class BaseFragment extends RxFragment {

    public Context getContext() {
        return getActivity().getApplicationContext();
    }
}
