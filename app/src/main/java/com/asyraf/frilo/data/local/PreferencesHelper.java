package com.asyraf.frilo.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.asyraf.frilo.injection.ApplicationContext;

import java.security.GeneralSecurityException;


@Singleton
public class PreferencesHelper {

    public static final String AES_KEY = "JANCOBON";

    public static final String PREF_FILE_NAME = "frilo_pref_file";

    public static final String TOKEN_TO_SERVER = "access_token";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /////======================Encrypted===============================////
    public void setTokenToServer(String i) {
        SharedPreferences.Editor editor = mPref.edit();
        try {
            editor.putString(TOKEN_TO_SERVER, AES.encrypt(AES_KEY, i));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public String getTokenToServer() {
        String access_token = null;
        try {
            access_token = AES.decrypt(AES_KEY, mPref.getString(TOKEN_TO_SERVER, ""));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return access_token;
    }

    //======================Not Encrypted===========================////


    public void clear() {
        mPref.edit().clear().apply();
    }

}
