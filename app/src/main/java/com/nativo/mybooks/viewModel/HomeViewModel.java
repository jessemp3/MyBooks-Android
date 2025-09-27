package com.nativo.mybooks.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nativo.mybooks.callBack.CallBack;
import com.nativo.mybooks.entity.BookEntity;
import com.nativo.mybooks.repository.BookRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private BookRepository bookRepository = BookRepository.getInstance(getApplication().getApplicationContext());
    private final MutableLiveData<List<BookEntity>> _books = new MutableLiveData<>();
    public final MutableLiveData<List<BookEntity>> books = _books;

    public HomeViewModel(@NonNull Application application) {
        super(application);


        bookRepository.getBooks(new CallBack<List<BookEntity>>() {
            @Override
            public void onSuccess(List<BookEntity> result) {
                if (result.isEmpty()){
                    bookRepository.loadInitialBooks();
                }
            }
        });
    }

    public void getBooks() {
        bookRepository.getBooks(new CallBack<List<BookEntity>>() {
            @Override
            public void onSuccess(List<BookEntity> result) {
                _books.postValue(result);
            }
        });
    }

    public void toggleFavoriteStatus(int id){
        bookRepository.toggleFavoriteStatus(id, new CallBack<Void>() {
            @Override
            public void onSuccess(Void result) {
                getBooks();
            }
        });
    }
}