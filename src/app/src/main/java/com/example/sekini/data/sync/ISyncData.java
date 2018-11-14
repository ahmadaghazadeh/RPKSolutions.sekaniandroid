package com.example.sekini.data.sync;

import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;

public interface ISyncData {

    void syncTables(ISyncListener syncListener) throws IOException, ApiException;

}
