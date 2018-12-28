package com.example.sekini.data.remote.api;


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
import com.example.sekini.data.remote.UserInfo;
import com.example.sekini.data.sync.DeletedList;
import com.example.sekini.data.sync.ExistList;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IApiRetrofitService {

    @GET("sync/englishWords/{timestamp}")
    Call<List<EnglishWordsEntity>> getEnglishWords(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniCategories/{timestamp}")
    Call<List<SekaniCategoriesEntity>> getSekaniCategories(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniDictionary/{timestamp}")
    Call<List<SekaniDictionaryEntity>> getSekaniDictionary(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniForms/{timestamp}")
    Call<List<SekaniFormsEntity>> getSekaniForms(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniLevels/{timestamp}")
    Call<List<SekaniLevelsEntity>> getSekaniLevels(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniRootImages/{timestamp}")
    Call<List<SekaniRootImagesEntity>> getSekaniRootImages(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniRoots_EnglishWords/{timestamp}")
    Call<List<SekaniRoots_EnglishWordsEntity>> getSekaniRoots_EnglishWords(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniRoots_Topics/{timestamp}")
    Call<List<SekaniRoots_TopicsEntity>> getSekaniRoots_Topics(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniRoots/{timestamp}")
    Call<List<SekaniRootsEntity>> getSekaniRoots(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniWordAttributes/{timestamp}")
    Call<List<SekaniWordAttributesEntity>> getSekaniWordAttributes(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniWordAudios/{timestamp}")
    Call<List<SekaniWordAudiosEntity>> getSekaniWordAudios(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniWordExampleAudios/{timestamp}")
    Call<List<SekaniWordExampleAudiosEntity>> getSekaniWordExampleAudios(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniWordExamples/{timestamp}")
    Call<List<SekaniWordExamplesEntity>> getSekaniWordExamples(@Path("timestamp") String timpstamp);

    @GET("sync/sekaniWords/{timestamp}")
    Call<List<SekaniWordsEntity>> getSekaniWords(@Path("timestamp") String timpstamp);

    @GET("sync/topics/{timestamp}")
    Call<List<TopicsEntity>> getTopics(@Path("timestamp") String timpstamp);

    @POST("sync/englishWordsDeleted")
    Call<DeletedList> getEnglishWordsDeleted(@Body ExistList existList);

    @POST("sync/sekaniCategoriesDeleted")
    Call<DeletedList> getSekaniCategoriesDeleted(@Body ExistList existList);

    @POST("sync/sekaniDictionaryDeleted")
    Call<DeletedList> getSekaniDictionaryDeleted(@Body ExistList existList);

    @POST("sync/sekaniFormsDeleted")
    Call<DeletedList> getSekaniFormsDeleted(@Body ExistList existList);

    @POST("sync/sekaniLevelsDeleted")
    Call<DeletedList> getSekaniLevelsDeleted(@Body ExistList existList);

    @POST("sync/sekaniRootImagesDeleted")
    Call<DeletedList> getSekaniRootImagesDeleted(@Body ExistList existList);

    @POST("sync/sekaniRoots_EnglishWordsDeleted")
    Call<DeletedList> getSekaniRoots_EnglishWordsDeleted(@Body ExistList existList);

    @POST("sync/sekaniRoots_TopicsDeleted")
    Call<DeletedList> getSekaniRoots_TopicsDeleted(@Body ExistList existList);

    @POST("sync/sekaniRootsDeleted")
    Call<DeletedList> getSekaniRootsDeleted(@Body ExistList existList);

    @POST("sync/sekaniWordAttributesDeleted")
    Call<DeletedList> getSekaniWordAttributesDeleted(@Body ExistList existList);

    @POST("sync/sekaniWordAudiosDeleted")
    Call<DeletedList> getSekaniWordAudiosDeleted(@Body ExistList existList);

    @POST("sync/sekaniWordExampleAudiosDeleted")
    Call<DeletedList> getSekaniWordExampleAudiosDeleted(@Body ExistList existList);

    @POST("sync/sekaniWordExamplesDeleted")
    Call<DeletedList> getSekaniWordExamplesDeleted(@Body ExistList existList);

    @POST("sync/sekaniWordsDeleted")
    Call<DeletedList> getSekaniWordsDeleted(@Body ExistList existList);

    @POST("sync/topicsDeleted")
    Call<DeletedList> getTopicsDeleted(@Body ExistList existList);


}
