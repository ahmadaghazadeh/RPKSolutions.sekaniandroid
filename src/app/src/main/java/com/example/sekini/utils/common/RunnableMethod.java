package com.example.sekini.utils.common;


import com.example.sekini.utils.exception.ApiException;

import java.io.IOException;

public interface RunnableMethod<TIN, TOUT> {
    TOUT run(TIN param, OnProgressUpdate onProgressUpdate);
}

