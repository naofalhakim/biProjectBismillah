package com.example.user.projectbidanku.AppConfiguration;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 16/09/2018.
 */

public class SessionManager {
    private SharedPreferences sharedPreferences;
    public final String KEY_EMAIL = "EMAIL";
    public final String KEY_ID = "ID";
    public final String KEY_LOGIN = "LOGIN";
    public final String SESSION = "SESSION_LOGIN";

    public SessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
    }

    public void setLogin(String email, int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(KEY_LOGIN, true);
        editor.commit();
    }

    public boolean isLogedIn() {
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    public int getLoginID() {
        return sharedPreferences.getInt(KEY_ID, 0);
    }

    public String getLoginEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public void doLogout () {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
