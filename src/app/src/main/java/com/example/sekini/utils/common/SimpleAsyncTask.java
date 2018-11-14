package com.example.sekini.utils.common;

import android.os.AsyncTask;


public class SimpleAsyncTask extends AsyncTask<Object, Object, Object> {
    private RunnableMethod runDo;
    private RunnableMethod runPre;
    private RunnableIn runPost;
    private OnProgressUpdate onProgressUpdate;

    public SimpleAsyncTask(RunnableMethod runPre,
                           RunnableMethod runDo, RunnableIn runPost, OnProgressUpdate onProgressUpdate) {
        this.runPre = runPre;
        this.runDo = runDo;
        this.runPost = runPost;
        this.onProgressUpdate = onProgressUpdate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (runPre != null) {
            runPre.run(null, null);
        }
    }

    @Override
    protected Object doInBackground(Object... obj) {
        Object objReturn = new Object();
        if (runDo != null) {
            OnProgressUpdate onProgressUpdate = new OnProgressUpdate() {

                @Override
                public void onProgressUpdate(String title) {
                    publishProgress(title);
                }

                @Override
                public void onProgressUpdate(String title, String message) {
                    publishProgress(title, message);
                }

                @Override
                public void onProgressUpdate(String title, String message, int count, int max) {
                    publishProgress(title, message, "" + count, "" + max);
                }

                @Override
                public void onProgressUpdate(String message, int count, int max) {
                    publishProgress(message, "" + count, "" + max);
                }
            };
            objReturn = runDo.run(null, onProgressUpdate);
        }
        return objReturn;
    }

    @Override
    protected void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        if (runPost != null) {
            runPost.run(obj);
        }
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        if (values != null)
            if (values.length == 1) {
                onProgressUpdate.onProgressUpdate(values[0].toString());
            } else if (values.length == 2) {
                onProgressUpdate.onProgressUpdate(values[0].toString(), values[1].toString());
            } else if (values.length == 4) {
                onProgressUpdate.onProgressUpdate(values[0].toString(), values[1].toString(),
                        Integer.parseInt(values[2].toString()), Integer.parseInt(values[3].toString()));
            } else if (values.length == 3) {
                onProgressUpdate.onProgressUpdate(
                        values[0].toString(),
                        Integer.parseInt(values[1].toString()),
                        Integer.parseInt(values[2].toString()));
            }
        super.onProgressUpdate(values);
    }
}
