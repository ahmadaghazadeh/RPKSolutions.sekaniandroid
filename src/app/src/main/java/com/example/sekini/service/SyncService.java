package com.example.sekini.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.sekini.R;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.data.sync.ISyncListener;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.common.SimpleAsyncTask;

import javax.inject.Inject;

import dagger.android.DaggerIntentService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SyncService extends DaggerIntentService {

    @Inject
    public ISyncData syncData;
    @Inject
    public CommonUtils commonUtils;


    public SyncService() {
        super("SyncService");

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SyncService.class);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            RunnableMethod<Object, RunnableModel> runnableMethod = (param, onProgressUpdate) -> {
                RunnableModel runnableModel = new RunnableModel();
                try {
                    syncData.syncTables((message, stage, count) -> commonUtils.notification(commonUtils.getString(R.string.attention), message, stage, count, 1));
                } catch (Exception e) {
                    runnableModel.setException(e);
                }
                return runnableModel;
            };

            RunnableIn<RunnableModel> post = (param) -> {
                if (param.hasError()) {
                    commonUtils.notification(commonUtils.getString(R.string.attention),
                            param.getException().getMessage(), 0, 0, 1);
                } else {
                    commonUtils.notification(commonUtils.getString(R.string.attention),
                            commonUtils.getString(R.string.sync_complete), 0, 0, 1);
                }
            };
            SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(null, runnableMethod, post, null);
            simpleAsyncTask.execute();
        }
    }


}
