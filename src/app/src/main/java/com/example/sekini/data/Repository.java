package com.example.sekini.data;

import android.content.Context;

import com.example.sekini.data.local.db.IEnglishWordsDao;
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
import com.example.sekini.data.remote.IApi;
import com.example.sekini.data.remote.RetrofitApi;
import com.example.sekini.data.sync.DeletedList;
import com.example.sekini.data.sync.ExistList;
import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;


public class Repository implements IRepository {

    private IApi api;
    private Context context;

    public Repository(IApi api, Context context) {
        this.api = api;
        this.context = context;
    }

    @Override
    public List<EnglishWordsEntity> getEnglishWords(String timeStamp) throws IOException, ApiException {
        Response<List<EnglishWordsEntity>> response = api.getEnglishWords(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniCategoriesEntity> getSekaniCategories(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniCategoriesEntity>> response = api.getSekaniCategories(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }



    @Override
    public List<SekaniFormsEntity> getSekaniForms(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniFormsEntity>> response = api.getSekaniForms(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniLevelsEntity> getSekaniLevels(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniLevelsEntity>> response = api.getSekaniLevels(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniRootImagesEntity> getSekaniRootImages(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniRootImagesEntity>> response = api.getSekaniRootImages(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniRoots_EnglishWordsEntity> getSekaniRoots_EnglishWords(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniRoots_EnglishWordsEntity>> response = api.getSekaniRoots_EnglishWords(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniRoots_TopicsEntity> getSekaniRoots_Topics(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniRoots_TopicsEntity>> response = api.getSekaniRoots_Topics(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniRootsEntity> getSekaniRoots(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniRootsEntity>> response = api.getSekaniRoots(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniWordAttributesEntity> getSekaniWordAttributes(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniWordAttributesEntity>> response = api.getSekaniWordAttributes(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniWordAudiosEntity> getSekaniWordAudios(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniWordAudiosEntity>> response = api.getSekaniWordAudios(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniWordExampleAudiosEntity> getSekaniWordExampleAudios(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniWordExampleAudiosEntity>> response = api.getSekaniWordExampleAudios(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniWordExamplesEntity> getSekaniWordExamples(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniWordExamplesEntity>> response = api.getSekaniWordExamples(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniWordsEntity> getSekaniWords(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniWordsEntity>> response = api.getSekaniWords(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<TopicsEntity> getTopics(String timeStamp) throws IOException, ApiException {
        Response<List<TopicsEntity>> response = api.getTopics(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getEnglishWordsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getEnglishWordsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniCategoriesDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniCategoriesDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }



    @Override
    public DeletedList getSekaniFormsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniFormsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniLevelsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniLevelsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniRootImagesDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniRootImagesDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniRoots_EnglishWordsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniRoots_EnglishWordsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniRoots_TopicsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniRoots_TopicsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniRootsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniRootsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniWordAttributesDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniWordAttributesDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniWordAudiosDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniWordAudiosDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniWordExampleAudiosDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniWordExampleAudiosDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniWordExamplesDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniWordExamplesDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getSekaniWordsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getSekaniWordsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public DeletedList getTopicsDeleted(ExistList existList) throws IOException, ApiException {
        Response<DeletedList> response = api.getTopicsDeleted(existList).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }


}
