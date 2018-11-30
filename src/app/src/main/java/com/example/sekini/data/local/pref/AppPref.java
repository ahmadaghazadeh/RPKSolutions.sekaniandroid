package com.example.sekini.data.local.pref;


import android.content.Context;

import com.example.sekini.data.remote.Token;


public class AppPref implements IAppPref {

    private static final String IS_INIT_APP = "IS_INIT_APP";
    private static final String USER_ID = "USER_ID";
    private static final String TOKEN = "TOKEN";
    private static final String TOKEN_EXPIRE_TIME = "TOKEN_EXPIRE_TIME";
    private static final String SCORE = "SCORE";
    private static final String LIFE = "LIFE";

    private PreferenceManager pm;


    public AppPref(Context context) {
        this.pm = new PreferenceManager(context);
    }


    @Override
    public boolean isInitApp() {
        return pm.get(IS_INIT_APP, false);
    }

    @Override
    public void setInitApp() {
        pm.set(IS_INIT_APP, true);
    }

    @Override
    public int getUserId() {
        return pm.get(USER_ID, -1);
    }

    @Override
    public void setUserId(int userId) {
        pm.set(USER_ID, userId);
    }

    @Override
    public String getToken() {
        return pm.get(TOKEN,"");
    }

    @Override
    public void setToken(String token) {
        pm.set(TOKEN, token);
    }

    @Override
    public void setScore(int score) {
        pm.set(SCORE, score);
    }

    @Override
    public int getScore() {
        return pm.get(SCORE,0);
    }

    @Override
    public void setLife(int life) {
        pm.set(LIFE, life);
    }

    @Override
    public int getLife() {
        return pm.get(LIFE,0);
    }

    @Override
    public void setTokenExpireTime(long l) {
        pm.set(TOKEN_EXPIRE_TIME, l);
    }

    @Override
    public long getTokenExpireTime() {
        return pm.get(TOKEN_EXPIRE_TIME,0L);
    }
}
