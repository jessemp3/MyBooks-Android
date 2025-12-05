package com.nativo.mybooks.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nativo.mybooks.R;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.repository.BookRepository;

import java.util.Objects;

public class AddBookDialogFragment extends DialogFragment {

    private EditText edtTitle, edtAuthor;
    private Spinner spinnerCategory;
    private Switch switchFavorite;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.dialog_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtTitle = view.findViewById(R.id.edtTitle);
        edtAuthor = view.findViewById(R.id.edtAuthor);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        switchFavorite = view.findViewById(R.id.switchFavorite);
        btnSave = view.findViewById(R.id.btnSave);

        setupSpinner();

        btnSave.setOnClickListener(v -> saveBook());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.6f);
        return dialog;
    }

    private void saveBook() {
        String title = edtTitle.getText().toString();
        String author = edtAuthor.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        boolean favorite = switchFavorite.isChecked();

        if (title.isEmpty()) {
            edtTitle.setError("Digite um título");
            return;
        }else if(author.isEmpty()) {
            edtAuthor.setError("Digite um autor");
            return;
        }else if(category.isEmpty()) {
            Toast.makeText(requireContext(), "Selecione uma categoria", Toast.LENGTH_SHORT).show();
            return;
        }

        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(category);
        book.setFavorite(favorite);

        BookRepository.getInstance(requireContext()).addBook(book, id -> {
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(requireContext(), "Livro adicionado!", Toast.LENGTH_SHORT).show();
                dismiss();
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT)
            );
        }
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.categories,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

}

