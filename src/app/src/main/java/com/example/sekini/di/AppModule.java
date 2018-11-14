package com.example.sekini.di;


import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.sekini.app.C;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.Repository;
import com.example.sekini.data.local.BookDataBase;
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
import com.example.sekini.data.local.db.embedded.ISekaniEnglishDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.remote.Api;
import com.example.sekini.data.remote.IApi;
import com.example.sekini.data.remote.RetrofitApi;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.data.sync.SyncData;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    @Named("serverUrl")
    public static String getBaseUrl() {
        return C.UrlApi;
    }

    @Singleton
    @Provides
    public static CommonUtils providesCommonUtils(Context context) {
        return new CommonUtils(context);
    }

    @Binds
    @Singleton
    abstract Context getContext(Application application);

    @Singleton
    @Provides
    public static BookDataBase providesDatabase(Context context) {
        return Room.databaseBuilder(context, BookDataBase.class, "Sekini.db").addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

        }).allowMainThreadQueries().build();
    }


    @Singleton
    @Provides
    public static IEnglishWordsDao provideEnglishWordsDao(BookDataBase dataBase) {
        return dataBase.getEnglishWordsDao();
    }

    @Singleton
    @Provides
    public static ISekaniRootDtoDao provideWordDtoDao(BookDataBase dataBase) {
        return dataBase.getSekaniRootDtoDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordDtoDao provideSekaniWordDto(BookDataBase dataBase) {
        return dataBase.getSekaniWordDtoDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordExampleDtoDao provideSekaniWordExampleDtoDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordExampleDtoDao();
    }


    @Singleton
    @Provides
    public static ISekaniCategoriesDao provideSekaniCategoriesDao(BookDataBase dataBase) {
        return dataBase.getSekaniCategoriesDao();
    }

    @Singleton
    @Provides
    public static ISekaniDictionaryDao provideSekaniDictionaryDao(BookDataBase dataBase) {
        return dataBase.getSekaniDictionaryDao();
    }

    @Singleton
    @Provides
    public static ISekaniFormsDao provideSekaniFormsDao(BookDataBase dataBase) {
        return dataBase.getSekaniFormsDao();
    }

    @Singleton
    @Provides
    public static ISekaniLevelsDao provideSekaniLevelsDao(BookDataBase dataBase) {
        return dataBase.getSekaniLevelsDao();
    }

    @Singleton
    @Provides
    public static ISekaniRootImagesDao provideSekaniRootImagesDao(BookDataBase dataBase) {
        return dataBase.getSekaniRootImagesDao();
    }

    @Singleton
    @Provides
    public static ISekaniRoots_EnglishWordsDao provideSekaniRoots_EnglishWordsDao(BookDataBase dataBase) {
        return dataBase.getSekaniRoots_EnglishWordsDao();
    }

    @Singleton
    @Provides
    public static ISekaniRoots_TopicsDao provideSekaniRoots_TopicsDao(BookDataBase dataBase) {
        return dataBase.getSekaniRoots_TopicsDao();
    }

    @Singleton
    @Provides
    public static ISekaniRootsDao provideSekaniRootsDao(BookDataBase dataBase) {
        return dataBase.getSekaniRootsDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordAttributesDao provideSekaniWordAttributesDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordAttributesDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordAudiosDao provideSekaniWordAudiosDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordAudiosDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordExampleAudiosDao provideSekaniWordExampleAudiosDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordExampleAudiosDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordExamplesDao provideSekaniWordExamplesDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordExamplesDao();
    }

    @Singleton
    @Provides
    public static ISekaniWordsDao provideSekaniWordsDao(BookDataBase dataBase) {
        return dataBase.getSekaniWordsDao();
    }

    @Singleton
    @Provides
    public static ISyncDao provideSyncDao(BookDataBase dataBase) {
        return dataBase.getSyncDao();
    }

    @Singleton
    @Provides
    public static ISekaniEnglishDicDao provideSekaniEnglishWordDao(BookDataBase dataBase) {
        return dataBase.getSekaniEnglishWordDtoDao();
    }

    @Singleton
    @Provides
    public static ITopicsDao provideTopicsDao(BookDataBase dataBase) {
        return dataBase.getTopicsDao();
    }

    @Singleton
    @Provides
    public static RetrofitApi provideRetrofitApi(Retrofit retrofit) {
        return new RetrofitApi(retrofit);
    }

    @Provides
    @Singleton
    public static IRepository provideRepository(IApi api, Context context) {
        return new Repository(api, context);
    }

    @Provides
    @Singleton
    public static IApi provideApi(Retrofit retrofit) {
        return new Api(retrofit);
    }


    @Provides
    @Singleton
    public static ISyncData provideSyncData(IEnglishWordsDao englishWordsDao, ISekaniCategoriesDao sekaniCategoriesDao, ISekaniDictionaryDao sekaniDictionaryDao,
                                            ISekaniFormsDao sekaniFormsDao, ISekaniLevelsDao sekaniLevelsDao, ISekaniRootImagesDao sekaniRootImagesDao,
                                            ISekaniRoots_EnglishWordsDao sekaniRoots_EnglishWordsDao, ISekaniRoots_TopicsDao sekaniRoots_TopicsDao,
                                            ISekaniRootsDao sekaniRootsDao, ISekaniWordAttributesDao sekaniWordAttributesDao,
                                            ISekaniWordAudiosDao sekaniWordAudiosDao, ISekaniWordExampleAudiosDao sekaniWordExampleAudiosDao,
                                            ISekaniWordExamplesDao sekaniWordExamplesDao, ISekaniWordsDao sekaniWordsDao,
                                            ISyncDao syncDao, ITopicsDao topicsDao, IRepository repository, Context context) {
        return new SyncData(englishWordsDao, sekaniCategoriesDao, sekaniDictionaryDao,
                sekaniFormsDao, sekaniLevelsDao, sekaniRootImagesDao,
                sekaniRoots_EnglishWordsDao, sekaniRoots_TopicsDao,
                sekaniRootsDao, sekaniWordAttributesDao,
                sekaniWordAudiosDao, sekaniWordExampleAudiosDao,
                sekaniWordExamplesDao, sekaniWordsDao,
                syncDao, topicsDao, repository, context);
    }


}
