package com.example.xianweili.googlebooklist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(MainActivity mainActivity, ArrayList<Book> books) {
        super(mainActivity, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.booklist_item, parent, false);
            holder = new ViewHolder();
            holder.bookImageView = (ImageView) convertView.findViewById(R.id.book_image);
            holder.titleView = (TextView) convertView.findViewById(R.id.title_view);
            holder.authorView = (TextView) convertView.findViewById(R.id.author_view);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.book_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book currentBook = getItem(position);

        holder.bookImageView.setImageBitmap(currentBook.getImage());
        holder.titleView.setText(currentBook.getTitle());
        holder.authorView.setText(currentBook.getAuthor());
        holder.descriptionView.setText(currentBook.getDescription());

        return convertView;
    }

    static class ViewHolder {
        ImageView bookImageView;
        TextView titleView;
        TextView authorView;
        TextView descriptionView;
    }

}
