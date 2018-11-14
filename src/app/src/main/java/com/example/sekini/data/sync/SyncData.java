package com.example.sekini.data.sync;

import android.content.Context;

import com.example.sekini.R;
import com.example.sekini.app.C;
import com.example.sekini.data.IRepository;
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
import com.example.sekini.data.model.SekaniLevelsEntity;
import com.example.sekini.data.model.SyncEntity;
import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;

public class SyncData implements ISyncData {

    public SyncData(IEnglishWordsDao englishWordsDao, ISekaniCategoriesDao sekaniCategoriesDao, ISekaniDictionaryDao sekaniDictionaryDao,
                    ISekaniFormsDao sekaniFormsDao, ISekaniLevelsDao sekaniLevelsDao, ISekaniRootImagesDao sekaniRootImagesDao,
                    ISekaniRoots_EnglishWordsDao sekaniRoots_EnglishWordsDao, ISekaniRoots_TopicsDao sekaniRoots_TopicsDao,
                    ISekaniRootsDao sekaniRootsDao, ISekaniWordAttributesDao sekaniWordAttributesDao,
                    ISekaniWordAudiosDao sekaniWordAudiosDao, ISekaniWordExampleAudiosDao sekaniWordExampleAudiosDao,
                    ISekaniWordExamplesDao sekaniWordExamplesDao, ISekaniWordsDao sekaniWordsDao,
                    ISyncDao syncDao, ITopicsDao topicsDao, IRepository repository, Context context) {
        this.englishWordsDao = englishWordsDao;
        this.sekaniCategoriesDao = sekaniCategoriesDao;
        this.sekaniDictionaryDao = sekaniDictionaryDao;
        this.sekaniFormsDao = sekaniFormsDao;
        this.sekaniLevelsDao = sekaniLevelsDao;
        this.sekaniRootImagesDao = sekaniRootImagesDao;
        this.sekaniRoots_EnglishWordsDao = sekaniRoots_EnglishWordsDao;
        this.sekaniRoots_TopicsDao = sekaniRoots_TopicsDao;
        this.sekaniRootsDao = sekaniRootsDao;
        this.sekaniWordAttributesDao = sekaniWordAttributesDao;
        this.sekaniWordAudiosDao = sekaniWordAudiosDao;
        this.sekaniWordExampleAudiosDao = sekaniWordExampleAudiosDao;
        this.sekaniWordExamplesDao = sekaniWordExamplesDao;
        this.sekaniWordsDao = sekaniWordsDao;
        this.syncDao = syncDao;
        this.topicsDao = topicsDao;
        this.repository = repository;
        this.context = context;
    }

    private IEnglishWordsDao englishWordsDao;
    private ISekaniCategoriesDao sekaniCategoriesDao;
    private ISekaniDictionaryDao sekaniDictionaryDao;
    private ISekaniFormsDao sekaniFormsDao;
    private ISekaniLevelsDao sekaniLevelsDao;
    private ISekaniRootImagesDao sekaniRootImagesDao;
    private ISekaniRoots_EnglishWordsDao sekaniRoots_EnglishWordsDao;
    private ISekaniRoots_TopicsDao sekaniRoots_TopicsDao;
    private ISekaniRootsDao sekaniRootsDao;
    private ISekaniWordAttributesDao sekaniWordAttributesDao;
    private ISekaniWordAudiosDao sekaniWordAudiosDao;
    private ISekaniWordExampleAudiosDao sekaniWordExampleAudiosDao;
    private ISekaniWordExamplesDao sekaniWordExamplesDao;
    private ISekaniWordsDao sekaniWordsDao;
    private ISyncDao syncDao;
    private ITopicsDao topicsDao;
    private IRepository repository;
    private Context context;

    @Override
    public void syncTables(ISyncListener syncListener) throws IOException, ApiException {


        int count = 28;
        int counter = 1;

        //14
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        ExistList existList = new ExistList(sekaniRootsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniRootsDao.delete(repository.getSekaniRootsDeleted(existList).extras);

        //13
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniWordsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniWordsDao.delete(repository.getSekaniWordsDeleted(existList).extras);

        //12
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniRoots_TopicsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniRoots_TopicsDao.delete(repository.getSekaniRoots_TopicsDeleted(existList).extras);

        //11
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniRoots_EnglishWordsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniRoots_EnglishWordsDao.delete(repository.getSekaniRoots_EnglishWordsDeleted(existList).extras);


        //10
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniWordAudiosDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniWordAudiosDao.delete(repository.getSekaniWordAudiosDeleted(existList).extras);

        //9
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniWordExamplesDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniWordExamplesDao.delete(repository.getSekaniWordExamplesDeleted(existList).extras);


        //8
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniCategoriesDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniCategoriesDao.delete(repository.getSekaniCategoriesDeleted(existList).extras);


        //7
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniWordExampleAudiosDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniWordExampleAudiosDao.delete(repository.getSekaniWordExampleAudiosDeleted(existList).extras);


        //6
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniWordAttributesDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniWordAttributesDao.delete(repository.getSekaniWordAttributesDeleted(existList).extras);


        //5
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniFormsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniFormsDao.delete(repository.getSekaniFormsDeleted(existList).extras);


        //4
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniRootImagesDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniRootImagesDao.delete(repository.getSekaniRootImagesDeleted(existList).extras);


        //3
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(englishWordsDao.getIds());
        if (existList.getIds().size() > 0)
            englishWordsDao.delete(repository.getEnglishWordsDeleted(existList).extras);

        //2
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(sekaniLevelsDao.getIds());
        if (existList.getIds().size() > 0)
            sekaniLevelsDao.delete(repository.getSekaniLevelsDeleted(existList).extras);

        //1
        syncListener.onUpdate(context.getString(R.string.sync_delete_items), counter, count);
        existList = new ExistList(topicsDao.getIds());
        if (existList.getIds().size() > 0)
            topicsDao.delete(repository.getTopicsDeleted(existList).extras);

        //----- Insert ----------------

        //1
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_levels), counter, count);
        String timeStamp = syncDao.getTimestamp(C.TableName.SekaniLevels.name());
        sekaniLevelsDao.insert(repository.getSekaniLevels(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniLevels.name(), sekaniLevelsDao.getMaxDate()));
        //2
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_topics), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.Topics.name());
        topicsDao.insert(repository.getTopics(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.Topics.name(), topicsDao.getMaxDate()));

        //3
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_category), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniCategories.name());
        sekaniCategoriesDao.insert(repository.getSekaniCategories(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniCategories.name(), sekaniCategoriesDao.getMaxDate()));


        //4
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_forms), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniForms.name());
        sekaniFormsDao.insert(repository.getSekaniForms(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniRootImages.name(), sekaniFormsDao.getMaxDate()));

        //5
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_english_word), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.EnglishWords.name());
        englishWordsDao.insert(repository.getEnglishWords(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.EnglishWords.name(), englishWordsDao.getMaxDate()));

        //6
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_roots), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniRoots.name());
        sekaniRootsDao.insert(repository.getSekaniRoots(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniRoots.name(), sekaniRootsDao.getMaxDate()));

        //7
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_words), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniWords.name());
        sekaniWordsDao.insert(repository.getSekaniWords(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniWords.name(), sekaniWordsDao.getMaxDate()));

        //8
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_word_attributes), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniWordAttributes.name());
        sekaniWordAttributesDao.insert(repository.getSekaniWordAttributes(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniWordAttributes.name(), sekaniWordAttributesDao.getMaxDate()));

        //9
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_word_examples), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniWordExamples.name());
        sekaniWordExamplesDao.insert(repository.getSekaniWordExamples(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniWordExamples.name(), sekaniWordExamplesDao.getMaxDate()));


        //10
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_word_example_audios), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniWordExampleAudios.name());
        sekaniWordExampleAudiosDao.insert(repository.getSekaniWordExampleAudios(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniWordExampleAudios.name(), sekaniWordExampleAudiosDao.getMaxDate()));



        //11
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_word_audios), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniWordAudios.name());
        sekaniWordsDao.insert(repository.getSekaniWords(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniWordAudios.name(), sekaniWordsDao.getMaxDate()));

        //12
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_roots_english_words), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniRoots_EnglishWords.name());
        sekaniRoots_EnglishWordsDao.insert(repository.getSekaniRoots_EnglishWords(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniRoots_EnglishWords.name(), sekaniRoots_EnglishWordsDao.getMaxDate()));

        //13
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_roots_topics), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniRoots_Topics.name());
        sekaniRoots_TopicsDao.insert(repository.getSekaniRoots_Topics(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniRoots_Topics.name(), sekaniRoots_TopicsDao.getMaxDate()));




        //14
        counter++;
        syncListener.onUpdate(context.getString(R.string.sync_sekani_root_images), counter, count);
        timeStamp = syncDao.getTimestamp(C.TableName.SekaniRootImages.name());
        sekaniRootImagesDao.insert(repository.getSekaniRootImages(timeStamp));
        syncDao.update(new SyncEntity(C.TableName.SekaniRootImages.name(), sekaniRootImagesDao.getMaxDate()));


    }


}
