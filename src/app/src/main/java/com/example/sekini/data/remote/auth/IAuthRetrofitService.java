package com.example.sekini.data.remote.auth;


import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniCategoriesEntity;
import com.example.sekini.data.model.SekaniDictionaryEntity;
import com.example.sekini.data.model.SekaniFormsEntity;
import com.example.sekini.data.model.SekaniLevelsEntity;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.SekaniRootsEntity;
import com.example.sekini.data.model.SekaniRoots_EnglishWordsEntity;
import com.example.sekini.data.model.SekaniRoots_TopicsEntity;
import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.SekaniWordAudiosEntity;
import com.example.sekini.data.model.SekaniWordExampleAudiosEntity;
import com.example.sekini.data.model.SekaniWordExamplesEntity;
import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.TopicsEntity;
import com.example.sekini.data.remote.Token;
import com.example.sekini.data.remote.UserInfo;
import com.example.sekini.data.sync.DeletedList;
import com.example.sekini.data.sync.ExistList;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IAuthRetrofitService {


    @FormUrlEncoded
    @POST("token")
    Call<Token> login(@Field("client_id") String client_id,
                      @Field("client_secret") String client_secret,
                      @Field("username") String username,
                      @Field("password") String password,
                      @Field("grant_type") String grant_type,
                      @Field("scope") String scope);

    @GET("UserActivityStats/get/life")
    Call<List<UserInfo>> getLife(@Header("Authorization") String token);

    @GET("UserActivityStats/get/score")
    Call<List<UserInfo>> getScore(@Header("Authorization") String token);

    @GET("UserActivityStats/get/level")
    Call<List<UserInfo>> getLevel(@Header("Authorization") String token);

    @POST("UserActivityStats/learntWords/post/{sekaniId}")
    Call<String> setLearntWords(@Header("Authorization") String token,@Path("sekaniId")String sekaniId);

    @POST("UserActivityStats/failedWords/post/{sekaniId}")
    Call<String> setFailedWords(@Header("Authorization") String token,@Path("sekaniId")String sekaniId);

    @PUT("UserActivityStats/Put")
    Call<UserInfo> putValue(@Header("Authorization") String token,@Body HashMap Value );

}
