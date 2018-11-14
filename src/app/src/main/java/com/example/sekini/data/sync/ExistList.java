package com.example.sekini.data.sync;

import java.util.List;

public class ExistList {
    public ExistList(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private List<Integer> ids;
}
