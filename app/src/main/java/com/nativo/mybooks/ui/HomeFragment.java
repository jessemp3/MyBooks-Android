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
import com.nativo.mybooks.databinding.FragmentHomeBinding;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.ui.adapter.BooksAdapter;
import com.nativo.mybooks.ui.helper.BookConstance;
import com.nativo.mybooks.ui.listener.BookListener;
import com.nativo.mybooks.viewModel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private final BooksAdapter adapter = new BooksAdapter();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.reciclerViewBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.reciclerViewBooks.setAdapter(adapter);


        setObservers();
        attachListeners();
        return binding.getRoot();
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
            }
        });
    }

    private void attachListeners() {
        // callback com lambda
        BookListener listener = new BookListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(BookConstance.BOOK_ID, id);

                NavHostFragment.findNavController(HomeFragment.this)
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