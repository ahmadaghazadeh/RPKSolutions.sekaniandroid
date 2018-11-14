package com.example.sekini.data.sync;

public interface ISyncListener {
    void onUpdate(String message,int stage,int count);
}
