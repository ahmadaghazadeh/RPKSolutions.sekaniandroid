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
import retrofit2.Retrofit;

public class Api implements IApi {

    Retrofit refrofit;

    public Api(Retrofit refrofit) {
        this.refrofit = refrofit;
    }

    @Override
    public Call<List<EnglishWordsEntity>> getEnglishWords(String timestamp) {
        return refrofit.create(IRetrofitService.class).getEnglishWords(timestamp);
    }

    @Override
    public Call<List<SekaniCategoriesEntity>> getSekaniCategories(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniCategories(timestamp);
    }

    @Override
    public Call<List<SekaniDictionaryEntity>> getSekaniDictionary(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniDictionary(timestamp);
    }

    @Override
    public Call<List<SekaniFormsEntity>> getSekaniForms(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniForms(timestamp);
    }

    @Override
    public Call<List<SekaniLevelsEntity>> getSekaniLevels(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniLevels(timestamp);
    }

    @Override
    public Call<List<SekaniRootImagesEntity>> getSekaniRootImages(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniRootImages(timestamp);
    }

    @Override
    public Call<List<SekaniRoots_EnglishWordsEntity>> getSekaniRoots_EnglishWords(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniRoots_EnglishWords(timestamp);
    }

    @Override
    public Call<List<SekaniRoots_TopicsEntity>> getSekaniRoots_Topics(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniRoots_Topics(timestamp);
    }

    @Override
    public Call<List<SekaniRootsEntity>> getSekaniRoots(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniRoots(timestamp);
    }

    @Override
    public Call<List<SekaniWordAttributesEntity>> getSekaniWordAttributes(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniWordAttributes(timestamp);
    }

    @Override
    public Call<List<SekaniWordAudiosEntity>> getSekaniWordAudios(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniWordAudios(timestamp);
    }

    @Override
    public Call<List<SekaniWordExampleAudiosEntity>> getSekaniWordExampleAudios(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniWordExampleAudios(timestamp);
    }

    @Override
    public Call<List<SekaniWordExamplesEntity>> getSekaniWordExamples(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniWordExamples(timestamp);
    }

    @Override
    public Call<List<SekaniWordsEntity>> getSekaniWords(String timestamp) {
        return refrofit.create(IRetrofitService.class).getSekaniWords(timestamp);
    }

    @Override
    public Call<List<TopicsEntity>> getTopics(String timestamp) {
        return refrofit.create(IRetrofitService.class).getTopics(timestamp);
    }

    @Override
    public Call<DeletedList> getEnglishWordsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getEnglishWordsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniCategoriesDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniCategoriesDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniDictionaryDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniDictionaryDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniFormsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniFormsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniLevelsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniLevelsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniRootImagesDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniRootImagesDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniRoots_EnglishWordsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniRoots_EnglishWordsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniRoots_TopicsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniRoots_TopicsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniRootsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniRootsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniWordAttributesDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniWordAttributesDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniWordAudiosDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniWordAudiosDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniWordExampleAudiosDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniWordExampleAudiosDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniWordExamplesDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniWordExamplesDeleted(existList);
    }

    @Override
    public Call<DeletedList> getSekaniWordsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getSekaniWordsDeleted(existList);
    }

    @Override
    public Call<DeletedList> getTopicsDeleted(ExistList existList) {
        return refrofit.create(IRetrofitService.class).getTopicsDeleted(existList);
    }

}
