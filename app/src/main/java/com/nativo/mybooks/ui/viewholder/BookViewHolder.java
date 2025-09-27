package com.nativo.mybooks.ui.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativo.mybooks.R;
import com.nativo.mybooks.databinding.ItemBookBinding;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.ui.listener.BookListener;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final ItemBookBinding item;
    private final BookListener listener;

    public BookViewHolder(@NonNull ItemBookBinding itemView , BookListener bookListener) {
        super(itemView.getRoot());
        item = itemView;
        listener = bookListener;
    }

    public void bind(BookEntity book) {
        item.textViewTitle.setText(book.getTitle());
        item.textViewGenre.setText(book.getGenre());
        item.textViewAuthor.setText(book.getAuthor());
        updateFavoriteIcon(book);
        setGenereBackgroundColor(book);


        item.textViewTitle.setOnClickListener(view -> listener.onClick(book.getId()));
        item.textViewAuthor.setOnClickListener(v -> listener.onClick(book.getId()) );
        item.imageViewFavorite.setOnClickListener(v -> listener.onFavoriteClick(book.getId()));
    }

    public void updateFavoriteIcon(BookEntity book) {
        if (book.isFavorite()) {
            item.imageViewFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            item.imageViewFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }
    }

    private void setGenereBackgroundColor(BookEntity book) {
        if (book.getGenre().equals("Fantasia")) {
            item.textViewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy);
        } else if (book.getGenre().equals("Terror")) {
            item.textViewGenre.setBackgroundResource(R.drawable.rounded_label_red);
        }else{
            item.textViewGenre.setBackgroundResource(R.drawable.rounded_label_teal);
        }
    }

}
