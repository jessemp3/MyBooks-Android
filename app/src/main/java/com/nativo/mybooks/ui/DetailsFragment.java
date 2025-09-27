package com.nativo.mybooks.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nativo.mybooks.R;
import com.nativo.mybooks.databinding.FragmentDetailsBinding;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.ui.helper.BookConstance;
import com.nativo.mybooks.viewModel.DetailsViewModel;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private DetailsViewModel viewModel;
    private int bookId = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle b) {
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        bookId = getArguments() != null ? getArguments().getInt(BookConstance.BOOK_ID) : 0;
        viewModel.getBookById(bookId);

        setObservers();
        setListeners();

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setObservers() {
        viewModel.book.observe(getViewLifecycleOwner(), new Observer<BookEntity>() {
            @Override
            public void onChanged(BookEntity book) {
                binding.textViewTitle.setText(book.getTitle());
                binding.textViewAuthorValue.setText(book.getAuthor());
                binding.textViewGenreValue.setText(book.getGenre());
                binding.checkboxFavorite.setChecked(book.isFavorite());
                setGenereBackgroundColor(book);
                viewModel.bookDeleted.observe(getViewLifecycleOwner(), isDeleted -> {
                    if (isDeleted) {
                        Toast.makeText(getContext(), R.string.msg_removed_successfully, Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                });

            }
        });
    }

    private void setGenereBackgroundColor(BookEntity book) {
        if (book.getGenre().equals("Fantasia")) {
            binding.textViewGenreValue.setBackgroundResource(R.drawable.rounded_label_fantasy);
        } else if (book.getGenre().equals("Terror")) {
            binding.textViewGenreValue.setBackgroundResource(R.drawable.rounded_label_red);
        } else {
            binding.textViewGenreValue.setBackgroundResource(R.drawable.rounded_label_teal);
        }
    }

    private void setListeners() {
        binding.checkboxFavorite.setOnClickListener(v -> {
            viewModel.toggleFavoriteStatus(bookId);
        });

        binding.imageViewBack.setOnClickListener(v -> {
            //isso simula o click de voltar
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        binding.buttonRemove.setOnClickListener(v -> {
            handleRemove();
        });

    }

    private void handleRemove() {
        Context context = getContext();
        if (context != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(getString(R.string.dialog_message_delete_item))
                    .setPositiveButton(R.string.dialog_positive_button_yes, (dialog, which) -> {
                        viewModel.delete(bookId);
                    })
                    .setNegativeButton(R.string.dialog_negative_button_no, (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.create().show();
        }
    }
}