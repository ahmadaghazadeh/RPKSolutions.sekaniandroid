package com.example.sekini.data;


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
import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public interface IRepository {

    List<EnglishWordsEntity> getEnglishWords (String timeStamp)throws IOException, ApiException;
    Token connect(String userName,String password) throws Exception;
    List<SekaniCategoriesEntity> getSekaniCategories(String timeStamp)throws IOException, ApiException;

    UserInfo getLife(String token) throws Exception;
    UserInfo getScore(String token) throws Exception;
    UserInfo getLevel(String token) throws Exception;
    UserInfo putLife(String token,int life) throws Exception;
    UserInfo putScore(String token,int score) throws Exception;

    String setLearntWords(String token, String sekaniId) throws Exception;
    String  setFailedWords(String token,String sekaniId) throws Exception;

    List<SekaniFormsEntity> getSekaniForms(String timeStamp)throws IOException, ApiException;
    List<SekaniLevelsEntity> getSekaniLevels(String timeStamp)throws IOException, ApiException;
    List<SekaniRootImagesEntity> getSekaniRootImages(String timeStamp)throws IOException, ApiException;
    List<SekaniRoots_EnglishWordsEntity> getSekaniRoots_EnglishWords(String timeStamp)throws IOException, ApiException;
    List<SekaniRoots_TopicsEntity> getSekaniRoots_Topics(String timeStamp)throws IOException, ApiException;
    List<SekaniRootsEntity> getSekaniRoots(String timeStamp)throws IOException, ApiException;
    List<SekaniWordAttributesEntity> getSekaniWordAttributes(String timeStamp)throws IOException, ApiException;
    List<SekaniWordAudiosEntity> getSekaniWordAudios(String timeStamp)throws IOException, ApiException;
    List<SekaniWordExampleAudiosEntity> getSekaniWordExampleAudios(String timeStamp)throws IOException, ApiException;
    List<SekaniWordExamplesEntity> getSekaniWordExamples(String timeStamp)throws IOException, ApiException;
    List<SekaniWordsEntity> getSekaniWords(String timeStamp)throws IOException, ApiException;
    List<TopicsEntity> getTopics(String timeStamp)throws IOException, ApiException;
    DeletedList getEnglishWordsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniCategoriesDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniFormsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniLevelsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniRootImagesDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniRoots_EnglishWordsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniRoots_TopicsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniRootsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniWordAttributesDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniWordAudiosDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniWordExampleAudiosDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniWordExamplesDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getSekaniWordsDeleted(ExistList existList)throws IOException, ApiException;
    DeletedList getTopicsDeleted(ExistList existList)throws IOException, ApiException;
}
