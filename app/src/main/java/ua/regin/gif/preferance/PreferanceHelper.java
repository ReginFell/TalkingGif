package ua.regin.gif.preferance;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

import ua.regin.gif.R;

@SharedPref(value = SharedPref.Scope.APPLICATION_DEFAULT)
public interface PreferanceHelper {

    @DefaultBoolean(value = false, keyRes = R.string.key_autoplay)
    boolean autoplay();

}