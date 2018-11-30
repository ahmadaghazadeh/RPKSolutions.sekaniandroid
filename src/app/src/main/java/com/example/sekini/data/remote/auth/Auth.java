package com.example.sekini.data.remote.auth;

import com.example.sekini.data.remote.Token;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Auth implements IAuth {

    private Retrofit refrofit;

    public Auth(Retrofit refrofit) {
        this.refrofit = refrofit;
    }


    @Override
    public Call<Token> connect(String userName, String password) {
        return refrofit.create(IAuthRetrofitService.class).login("resourceOwner", "secret",
                userName, password, "password", "api1 offline_access");
    }
}
