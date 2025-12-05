package com.nativo.mybooks.repository;

import android.content.Context;

import com.nativo.mybooks.callBack.CallBack;
import com.nativo.mybooks.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class BookRepository {
    private static BookRepository instance;

    private final BookDAO dataBase;


    private BookRepository(Context context) {
        dataBase = BookDataBase.getInstance(context).bookDAO();
    }

    //padrão singleton
    public static BookRepository getInstance(Context context) {
        // o "synchronized" evita que duas threads acessem o método ao mesmo tempo
        synchronized (BookRepository.class) {
            if (instance == null) {
                instance = new BookRepository(context);
            }
        }
        return instance;
    }


    public void getBooks(CallBack<List<BookEntity>> callBack) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<BookEntity> list = dataBase.getBooks();
            callBack.onSuccess(list);
        });
    }

    // novo método para adicionar um livro
    public void addBook(BookEntity book, CallBack<Long> callBack) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long id = dataBase.createItem(book); // insere e obtém id
            callBack.onSuccess(id);
        });
    }

    public void getFavoriteBooks(CallBack<List<BookEntity>> callBack) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess( dataBase.getFavoriteBooks() );
            }
        });
    }

    public void getBookById(int id, CallBack<BookEntity> callBack) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess( dataBase.getBookById(id) );
            }
        });
    }

    public void toggleFavoriteStatus(int id , CallBack<Void> callBack) {


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                BookEntity book = dataBase.getBookById(id);
                book.setFavorite(!book.isFavorite());
                dataBase.update(book);

                callBack.onSuccess(null);
            }
        });
    }

    public void delete(int id,CallBack<Boolean> callBack) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                BookEntity book = dataBase.getBookById(id);
                callBack.onSuccess(dataBase.delete(book) > 0);
            }
        });
    }


    public void loadInitialBooks() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dataBase.create(getInitialBooks());
            }
        });
    }

    //a lista inicial de livros
    private List<BookEntity> getInitialBooks() {
        List<BookEntity> initialBooks = new ArrayList<>();
        initialBooks.add(new BookEntity(1, "To Kill a Mockingbird", "Harper Lee", true, "Ficção"));
        initialBooks.add(new BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"));
        initialBooks.add(new BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"));
        initialBooks.add(new BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"));
        initialBooks.add(new BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"));
        initialBooks.add(new BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"));
        initialBooks.add(new BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"));
        initialBooks.add(new BookEntity(8, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", false, "Fantasia"));
        initialBooks.add(new BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"));
        initialBooks.add(new BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"));
        initialBooks.add(new BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"));
        initialBooks.add(new BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"));
        initialBooks.add(new BookEntity(13, "Harry Potter e a Câmara Secreta", "J.K. Rowling", false, "Fantasia"));
        initialBooks.add(new BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"));
        initialBooks.add(new BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"));
        initialBooks.add(new BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"));
        initialBooks.add(new BookEntity(17, "Moby Dick", "Herman Melville", true, "Aventura"));
        initialBooks.add(new BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", true, "Fantasia"));
        initialBooks.add(new BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", true, "Aventura"));
        initialBooks.add(new BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance"));
        return initialBooks;
    }

}
