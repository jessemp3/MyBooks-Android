package com.nativo.mybooks.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativo.mybooks.databinding.ItemBookBinding;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.ui.listener.BookListener;
import com.nativo.mybooks.ui.viewholder.BookViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<BookEntity> bookList = new ArrayList<>();
    private BookListener listener;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding view = ItemBookBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new BookViewHolder(view , listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void updateBooks(List<BookEntity> books) {
        bookList = books;
        notifyDataSetChanged();
    }

    public void attachListeners(BookListener bookListener){
        listener = bookListener;
    }
}
