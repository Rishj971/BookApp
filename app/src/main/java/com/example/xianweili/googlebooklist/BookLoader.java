package com.example.xianweili.googlebooklist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.List;

/**
 * Created by xianwei li on 7/28/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String url;

    protected BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (url == null) {
            return null;
        }
        List<Book> books = QueryUtils.fetchBooks(url);
        return books;
    }
}
