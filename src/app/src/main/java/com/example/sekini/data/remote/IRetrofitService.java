package com.example.sekini.data.remote;


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
import com.example.sekini.data.sync.DeletedList;
import com.example.sekini.data.sync.ExistList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRetrofitService {

    @GET("englishWords/{timestamp}")
    Call<List<EnglishWordsEntity>> getEnglishWords(@Path("timestamp") String timpstamp);

    @GET("sekaniCategories/{timestamp}")
    Call<List<SekaniCategoriesEntity>> getSekaniCategories(@Path("timestamp") String timpstamp);

    @GET("sekaniDictionary/{timestamp}")
    Call<List<SekaniDictionaryEntity>> getSekaniDictionary(@Path("timestamp") String timpstamp);

    @GET("sekaniForms/{timestamp}")
    Call<List<SekaniFormsEntity>> getSekaniForms(@Path("timestamp") String timpstamp);

    @GET("sekaniLevels/{timestamp}")
    Call<List<SekaniLevelsEntity>> getSekaniLevels(@Path("timestamp") String timpstamp);

    @GET("sekaniRootImages/{timestamp}")
    Call<List<SekaniRootImagesEntity>> getSekaniRootImages(@Path("timestamp") String timpstamp);

    @GET("sekaniRoots_EnglishWords/{timestamp}")
    Call<List<SekaniRoots_EnglishWordsEntity>> getSekaniRoots_EnglishWords(@Path("timestamp") String timpstamp);

    @GET("sekaniRoots_Topics/{timestamp}")
    Call<List<SekaniRoots_TopicsEntity>> getSekaniRoots_Topics(@Path("timestamp") String timpstamp);

    @GET("sekaniRoots/{timestamp}")
    Call<List<SekaniRootsEntity>> getSekaniRoots(@Path("timestamp") String timpstamp);

    @GET("sekaniWordAttributes/{timestamp}")
    Call<List<SekaniWordAttributesEntity>> getSekaniWordAttributes(@Path("timestamp") String timpstamp);

    @GET("sekaniWordAudios/{timestamp}")
    Call<List<SekaniWordAudiosEntity>> getSekaniWordAudios(@Path("timestamp") String timpstamp);

    @GET("sekaniWordExampleAudios/{timestamp}")
    Call<List<SekaniWordExampleAudiosEntity>> getSekaniWordExampleAudios(@Path("timestamp") String timpstamp);

    @GET("sekaniWordExamples/{timestamp}")
    Call<List<SekaniWordExamplesEntity>> getSekaniWordExamples(@Path("timestamp") String timpstamp);

    @GET("sekaniWords/{timestamp}")
    Call<List<SekaniWordsEntity>> getSekaniWords(@Path("timestamp") String timpstamp);

    @GET("topics/{timestamp}")
    Call<List<TopicsEntity>> getTopics(@Path("timestamp") String timpstamp);

    @POST("englishWordsDeleted")
    Call<DeletedList> getEnglishWordsDeleted(@Body ExistList existList);

    @POST("sekaniCategoriesDeleted")
    Call<DeletedList> getSekaniCategoriesDeleted(@Body ExistList existList);

    @POST("sekaniDictionaryDeleted")
    Call<DeletedList> getSekaniDictionaryDeleted(@Body ExistList existList);

    @POST("sekaniFormsDeleted")
    Call<DeletedList> getSekaniFormsDeleted(@Body ExistList existList);

    @POST("sekaniLevelsDeleted")
    Call<DeletedList> getSekaniLevelsDeleted(@Body ExistList existList);

    @POST("sekaniRootImagesDeleted")
    Call<DeletedList> getSekaniRootImagesDeleted(@Body ExistList existList);

    @POST("sekaniRoots_EnglishWordsDeleted")
    Call<DeletedList> getSekaniRoots_EnglishWordsDeleted(@Body ExistList existList);

    @POST("sekaniRoots_TopicsDeleted")
    Call<DeletedList> getSekaniRoots_TopicsDeleted(@Body ExistList existList);

    @POST("sekaniRootsDeleted")
    Call<DeletedList> getSekaniRootsDeleted(@Body ExistList existList);

    @POST("sekaniWordAttributesDeleted")
    Call<DeletedList> getSekaniWordAttributesDeleted(@Body ExistList existList);

    @POST("sekaniWordAudiosDeleted")
    Call<DeletedList> getSekaniWordAudiosDeleted(@Body ExistList existList);

    @POST("sekaniWordExampleAudiosDeleted")
    Call<DeletedList> getSekaniWordExampleAudiosDeleted(@Body ExistList existList);

    @POST("sekaniWordExamplesDeleted")
    Call<DeletedList> getSekaniWordExamplesDeleted(@Body ExistList existList);

    @POST("sekaniWordsDeleted")
    Call<DeletedList> getSekaniWordsDeleted(@Body ExistList existList);

    @POST("topicsDeleted")
    Call<DeletedList> getTopicsDeleted(@Body ExistList existList);

}
