package com.example.sekini.data.local.pref;


import android.content.Context;

import com.example.sekini.data.remote.Token;


public class AppPref implements IAppPref {

    private static final String IS_INIT_APP = "IS_INIT_APP";
    private static final String USER_ID = "USER_ID";
    private static final String TOKEN = "TOKEN";
    private static final String TOKEN_EXPIRE_TIME = "TOKEN_EXPIRE_TIME";
    private static final String USERNAME = "USERNAME";
    private static final String SCORE = "SCORE";
    private static final String LIFE = "LIFE";
    private static final String PASSWORD = "PASSWORD";

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
        return pm.get(TOKEN, "");
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
        return pm.get(SCORE, 0);
    }

    @Override
    public void setLife(int life) {
        pm.set(LIFE, life);
    }

    @Override
    public int getLife() {
        int life = pm.get(LIFE, 0);
        return life > 5 ? 5 : life;
    }

    @Override
    public void setTokenExpireTime(long l) {
        pm.set(TOKEN_EXPIRE_TIME, l);
    }

    @Override
    public long getTokenExpireTime() {
        return pm.get(TOKEN_EXPIRE_TIME, 0L);
    }

    @Override
    public void setUserName(String value) {
        pm.set(USERNAME, value);
    }

    @Override
    public String getUserName() {
        return pm.get(USERNAME, "");
    }

    @Override
    public boolean isLogin() {
        return !getToken().isEmpty();
    }

    @Override
    public void setPassword(String value) {
        pm.set(PASSWORD, value);
    }

    @Override
    public String getPassword() {
        return pm.get(PASSWORD, "");
    }


}
