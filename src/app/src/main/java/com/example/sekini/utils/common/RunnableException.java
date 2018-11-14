package com.example.sekini.utils.common;

public interface RunnableException<TIN> {
    void run(TIN param, Exception ex);
}

