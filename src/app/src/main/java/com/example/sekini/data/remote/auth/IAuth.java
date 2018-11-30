package com.example.sekini.data.remote.auth;

import com.example.sekini.data.remote.Token;

import retrofit2.Call;


public interface IAuth {

     Call<Token> connect(String userName, String password);

}
