package com.example.xianweili.googlebooklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    @BindView(R.id.progressbar_view) ProgressBar progressBarView;
    @BindView(R.id.list) ListView listView;
    @BindView(R.id.empty_list) TextView emptyView;
    @BindView(R.id.button) Button SearchButton;
    @BindView(R.id.search_text) EditText searchTextView;

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final int BOOK_LOADER_ID = 1;
    private BookAdapter bookAdapter;
    private String createSearchUrl;
    private final String Urlpostfix = "&maxResults=25&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/imageLinks,volumeInfo/previewLink,searchInfo/textSnippet)";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupUI();
    }


    @OnClick(R.id.button)
    void ButtonOnClick() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            emptyView.setText(R.string.google_it);
            progressBarView.setVisibility(View.VISIBLE);
            String inputString = searchTextView.getText().toString();
            createSearchUrl = createSearchUrl(inputString);
            getLoaderManager().restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
        } else {
            bookAdapter.clear();
            emptyView.setText(R.string.no_intern);
        }
    }

    @OnItemClick(R.id.list)
    void onItemClick(int position) {
        Book currentBook = bookAdapter.getItem(position);
        Uri bookUri = Uri.parse(currentBook.getPreviewUrl());
        Intent website = new Intent(Intent.ACTION_VIEW, bookUri);
        startActivity(website);
    }

    private void setupUI() {

        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(bookAdapter);
        emptyView.setText(R.string.google_it);
        listView.setEmptyView(emptyView);
        getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);

    }

    private String createSearchUrl(String inputString) {
        StringBuilder url = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        return url.append(inputString.replace(" ", "+")).append(Urlpostfix).toString();
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, createSearchUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        progressBarView.setVisibility(View.GONE);
        bookAdapter.clear();
        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {bookAdapter.clear();
    }
}
