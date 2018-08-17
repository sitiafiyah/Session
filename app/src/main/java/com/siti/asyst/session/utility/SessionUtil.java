package com.siti.asyst.session.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtil {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static final String IS_LOGIN = "IsLoggedIn";
    Context mContex;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionUtil(Context context) {
        this.mContex = context;

        preferences = context.getSharedPreferences("training", 0); //parameter 1 nama, mode = 0 (private)

        editor = preferences.edit();
    }

    public void saveUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public String loadUsername() {
        String username = preferences.getString(KEY_USERNAME, "");
        return username;
    }

    public void savePassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public String loadPassword() {
        String password = preferences.getString(KEY_PASSWORD, "");
        return password;
    }

    //login
    public void saveLogin(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public Boolean getLogin() {
        return preferences.getBoolean(IS_LOGIN, false);
    }


}
