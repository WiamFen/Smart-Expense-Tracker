package net.wiam.smartexpensetracker;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public String getName() {
        return sharedPreferences.getString(KEY_NAME, "User");
    }

    // SAVE USER
    public void saveUser(String name, String email, String password) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    // CHECK LOGIN
    public boolean checkUser(String email, String password) {

        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);

        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    // CHECK IF USER EXISTS
    public boolean userExists() {
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }
}