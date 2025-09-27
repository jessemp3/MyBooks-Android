package com.nativo.mybooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nativo.mybooks.R;
import com.nativo.mybooks.databinding.FragmentFavoriteBinding;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.ui.adapter.BooksAdapter;
import com.nativo.mybooks.ui.helper.BookConstance;
import com.nativo.mybooks.ui.listener.BookListener;
import com.nativo.mybooks.viewModel.FavoriteViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private final BooksAdapter adapter = new BooksAdapter();
    private FavoriteViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle b) {
        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);

        binding.reciclerViewFavoriteBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.reciclerViewFavoriteBooks.setAdapter(adapter);

        setObservers();
        attachListeners();

        return  binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.getBooks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setObservers() {
        viewModel.books.observe(getViewLifecycleOwner(), new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(List<BookEntity> bookEntities) {
                adapter.updateBooks(bookEntities);

                if (bookEntities.isEmpty()) {
                   showEmptyList(bookEntities.isEmpty());
                } else {
                    showEmptyList(false);
                }
            }
        });
    }

    private void showEmptyList(boolean isEmpty) {
        binding.reciclerViewFavoriteBooks.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        binding.textViewNoBooks.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        binding.imageViewNoBooks.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    private void attachListeners() {
        // callback com lambda
        BookListener listener = new BookListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(BookConstance.BOOK_ID, id);

                NavHostFragment.findNavController(FavoriteFragment.this)
                        .navigate(R.id.navigation_details , bundle);
            }

            @Override
            public void onFavoriteClick(int id) {
                viewModel.toggleFavoriteStatus(id);
            }
        };
        adapter.attachListeners(listener);
    }
}