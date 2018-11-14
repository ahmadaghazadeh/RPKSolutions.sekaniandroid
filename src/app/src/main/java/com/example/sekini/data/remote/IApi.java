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


public interface IApi {

     Call<List<EnglishWordsEntity>> getEnglishWords(String timpstamp);
     Call<List<SekaniCategoriesEntity>> getSekaniCategories(String timpstamp);
     Call<List<SekaniDictionaryEntity>> getSekaniDictionary(String timpstamp);
     Call<List<SekaniFormsEntity>> getSekaniForms(String timpstamp);
     Call<List<SekaniLevelsEntity>> getSekaniLevels(String timpstamp);
     Call<List<SekaniRootImagesEntity>> getSekaniRootImages(String timpstamp);
     Call<List<SekaniRoots_EnglishWordsEntity>> getSekaniRoots_EnglishWords(String timpstamp);
     Call<List<SekaniRoots_TopicsEntity>> getSekaniRoots_Topics(String timpstamp);
     Call<List<SekaniRootsEntity>> getSekaniRoots(String timpstamp);
     Call<List<SekaniWordAttributesEntity>> getSekaniWordAttributes(String timpstamp);
     Call<List<SekaniWordAudiosEntity>> getSekaniWordAudios(String timpstamp);
     Call<List<SekaniWordExampleAudiosEntity>> getSekaniWordExampleAudios(String timpstamp);
     Call<List<SekaniWordExamplesEntity>> getSekaniWordExamples(String timpstamp);
     Call<List<SekaniWordsEntity>> getSekaniWords(String timpstamp);
     Call<List<TopicsEntity>> getTopics(String timpstamp);

     Call<DeletedList> getEnglishWordsDeleted( ExistList existList);
     Call<DeletedList> getSekaniCategoriesDeleted( ExistList existList);
     Call<DeletedList> getSekaniDictionaryDeleted( ExistList existList);
     Call<DeletedList> getSekaniFormsDeleted( ExistList existList);
     Call<DeletedList> getSekaniLevelsDeleted( ExistList existList);
     Call<DeletedList> getSekaniRootImagesDeleted( ExistList existList);
     Call<DeletedList> getSekaniRoots_EnglishWordsDeleted( ExistList existList);
     Call<DeletedList> getSekaniRoots_TopicsDeleted( ExistList existList);
     Call<DeletedList> getSekaniRootsDeleted( ExistList existList);
     Call<DeletedList> getSekaniWordAttributesDeleted( ExistList existList);
     Call<DeletedList> getSekaniWordAudiosDeleted( ExistList existList);
     Call<DeletedList> getSekaniWordExampleAudiosDeleted( ExistList existList);
     Call<DeletedList> getSekaniWordExamplesDeleted( ExistList existList);
     Call<DeletedList> getSekaniWordsDeleted( ExistList existList);
     Call<DeletedList> getTopicsDeleted( ExistList existList);
}
