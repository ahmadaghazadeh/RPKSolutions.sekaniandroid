package com.example.sekini.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sekini.data.local.db.IEnglishWordsDao;
import com.example.sekini.data.local.db.ISekaniCategoriesDao;
import com.example.sekini.data.local.db.ISekaniDictionaryDao;
import com.example.sekini.data.local.db.ISekaniFormsDao;
import com.example.sekini.data.local.db.ISekaniLevelsDao;
import com.example.sekini.data.local.db.ISekaniRootImagesDao;
import com.example.sekini.data.local.db.ISekaniRootsDao;
import com.example.sekini.data.local.db.ISekaniRoots_EnglishWordsDao;
import com.example.sekini.data.local.db.ISekaniRoots_TopicsDao;
import com.example.sekini.data.local.db.ISekaniWordAttributesDao;
import com.example.sekini.data.local.db.ISekaniWordAudiosDao;
import com.example.sekini.data.local.db.ISekaniWordExampleAudiosDao;
import com.example.sekini.data.local.db.ISekaniWordExamplesDao;
import com.example.sekini.data.local.db.ISekaniWordsDao;
import com.example.sekini.data.local.db.ISyncDao;
import com.example.sekini.data.local.db.ITopicsDao;
import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
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
import com.example.sekini.data.model.SyncEntity;
import com.example.sekini.data.model.TopicsEntity;



@Database(entities = {
        EnglishWordsEntity.class,
        SekaniCategoriesEntity.class,
        SekaniDictionaryEntity.class,
        SekaniFormsEntity.class,
        SekaniLevelsEntity.class,
        SekaniRootImagesEntity.class,
        SekaniRoots_EnglishWordsEntity.class,
        SekaniRoots_TopicsEntity.class,
        SekaniRootsEntity.class,
        SekaniWordAttributesEntity.class,
        SekaniWordAudiosEntity.class,
        SekaniWordExampleAudiosEntity.class,
        SekaniWordExamplesEntity.class,
        SekaniWordsEntity.class,
        SyncEntity.class,
        TopicsEntity.class
}, version = BookDataBase.VERSION)
public abstract class BookDataBase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract IEnglishWordsDao getEnglishWordsDao();
    public abstract ISekaniCategoriesDao getSekaniCategoriesDao();
    public abstract ISekaniDictionaryDao getSekaniDictionaryDao();
    public abstract ISekaniFormsDao getSekaniFormsDao();
    public abstract ISekaniLevelsDao getSekaniLevelsDao();
    public abstract ISekaniRootImagesDao getSekaniRootImagesDao();
    public abstract ISekaniRoots_EnglishWordsDao getSekaniRoots_EnglishWordsDao();
    public abstract ISekaniRoots_TopicsDao getSekaniRoots_TopicsDao();
    public abstract ISekaniRootsDao getSekaniRootsDao();
    public abstract ISekaniWordAttributesDao getSekaniWordAttributesDao();
    public abstract ISekaniWordAudiosDao getSekaniWordAudiosDao();
    public abstract ISekaniWordExampleAudiosDao getSekaniWordExampleAudiosDao();
    public abstract ISekaniWordExamplesDao getSekaniWordExamplesDao();
    public abstract ISekaniWordsDao getSekaniWordsDao();
    public abstract ISyncDao getSyncDao();
    public abstract ITopicsDao getTopicsDao();
    public abstract IDicDao getSekaniEnglishWordDtoDao();
    public abstract ISekaniRootDtoDao getSekaniRootDtoDao();
    public abstract ISekaniWordDtoDao getSekaniWordDtoDao();
    public abstract ISekaniWordExampleDtoDao getSekaniWordExampleDtoDao();

}
