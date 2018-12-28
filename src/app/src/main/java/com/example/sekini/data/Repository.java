package com.example.sekini.data;

import android.content.Context;

import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniCategoriesEntity;
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
import com.example.sekini.data.remote.api.IApi;
import com.example.sekini.data.remote.auth.IAuth;
import com.example.sekini.data.sync.DeletedList;
import com.example.sekini.data.sync.ExistList;
import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;


public class Repository implements IRepository {


    private IApi api;
    private Context context;
    private IAuth auth;
    private IAppPref appPref;

    public Repository(IApi api, IAuth auth, IAppPref appPref, Context context) {
        this.api = api;
        this.auth = auth;
        this.context = context;
        this.appPref = appPref;
    }


    @Override
    public List<EnglishWordsEntity> getEnglishWords(String timeStamp) throws IOException, ApiException {
        Response<List<EnglishWordsEntity>> response = api.getEnglishWords(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public Token connect(String userName, String password) throws Exception {
        Response<Token> response;
        try {
            response = auth.connect(userName, password).execute();
        } catch (Exception ex) {
            throw new Exception("Can't log in to Sekani");
        }
        if (response != null && response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public List<SekaniCategoriesEntity> getSekaniCategories(String timeStamp) throws IOException, ApiException {
        Response<List<SekaniCategoriesEntity>> response = api.getSekaniCategories(timeStamp).execute();
        if (response.isSuccessful()) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public UserInfo getLife(String accessToken) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<List<UserInfo>> response = api.getLife(accessToken).execute();
        if (response.isSuccessful() && response.body() != null) return response.body().get(0);
        throw new ApiException(context, response);
    }

    @Override
    public UserInfo getScore(String accessToken) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<List<UserInfo>> response = api.getScore(accessToken).execute();
        if (response.isSuccessful() && response.body() != null) return response.body().get(0);
        throw new ApiException(context, response);
    }

    @Override
    public UserInfo getLevel(String accessToken) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<List<UserInfo>> response = api.getLevel(accessToken).execute();
        if (response.isSuccessful() && response.body() != null) return response.body().get(0);
        throw new ApiException(context, response);
    }

    @Override
    public UserInfo putLife(String accessToken, int life) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        if (life <= 0)
            life = 0;
        Response<UserInfo> response = api.putLife(accessToken, "" + life).execute();
        if (response.isSuccessful() && response.body() != null) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public UserInfo putScore(String accessToken, int score) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<UserInfo> response = api.putScore(accessToken, "" + score).execute();
        if (response.isSuccessful() && response.body() != null) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public String setLearntWords(String accessToken, String sekaniId) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<String> response = api.setLearntWords(accessToken, sekaniId).execute();
        if (response.isSuccessful() && response.body() != null) return response.body();
        throw new ApiException(context, response);
    }

    @Override
    public String setFailedWords(String accessToken, String sekaniId) throws Exception {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()) {
            Token token = connect(appPref.getUserName(), appPref.getPassword());
            appPref.setToken(token.access_token);
            time = System.currentTimeMillis();
            appPref.setTokenExpireTime(time + token.expires_in * 1000);
        }
        Response<String> response = api.setFailedWords(accessToken, sekaniId).execute();
        if (response.isSuccessful() && response.body() != null) return response.body();
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
