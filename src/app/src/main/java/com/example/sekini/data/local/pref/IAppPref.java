package com.example.sekini.data.local.pref;


public interface IAppPref {

    boolean isInitApp();

    void setInitApp();

    int getUserId();

    void setUserId(int userId);

    String getToken();

    void setToken(String token);

    void setScore(int score);

    int getScore();

    void setLife(int life);

    int getLife();

    void setTokenExpireTime(long l);

    long getTokenExpireTime();

    void setUserName(String value);

    String getUserName();

    boolean isLogin();

    void setPassword(String value);

    String getPassword();
}
