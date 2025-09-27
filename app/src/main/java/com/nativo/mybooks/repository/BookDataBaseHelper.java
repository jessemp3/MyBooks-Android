//package com.nativo.mybooks.repository;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import com.nativo.mybooks.entity.BookEntity;
//import com.nativo.mybooks.ui.helper.DataBaseContance;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookDataBaseHelper extends
//        SQLiteOpenHelper {
//
//    private static final int VERSION = 1;
//    private static final String NAME = "book_database";
//
//    String createBookTable = "CREATE TABLE " + DataBaseContance.Book.TABLE_NAME + " (" +
//            DataBaseContance.Book.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            DataBaseContance.Book.Columns.TITLE + " TEXT NOT NULL, " +
//            DataBaseContance.Book.Columns.AUTHOR + " TEXT NOT NULL, " +
//            DataBaseContance.Book.Columns.GENRE + " TEXT NOT NULL, " +
//            DataBaseContance.Book.Columns.FAVORITE + " INTEGER NOT NULL );";
//
//    public BookDataBaseHelper(Context context) {
//        super(context, NAME, null, VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Executa a criação da tabela no banco
//        db.execSQL(createBookTable);
//
//        // Uma vez criada, executa a inserção da massa de dados
//        insertBooks(db);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        // Deleta a tabela e cria novamente (uma maneira de lidar com atualizações)
//        db.execSQL("DROP TABLE IF EXISTS " + DataBaseContance.Book.TABLE_NAME);
//
//        // Cria novamente usando o novo schema
//        onCreate(db);
//    }
//
//
//    private void insertBooks(SQLiteDatabase db) {
//        List<BookEntity> books = getInitialBooks();
//        for (BookEntity book : books) {
//            ContentValues values = new ContentValues();
//            values.put(DataBaseContance.Book.Columns.TITLE, book.getTitle());
//            values.put(DataBaseContance.Book.Columns.AUTHOR, book.getAuthor());
//            // Converte boolean para inteiro
//            values.put(DataBaseContance.Book.Columns.FAVORITE, book.isFavorite() ? 1 : 0);
//            values.put(DataBaseContance.Book.Columns.GENRE, book.getGenre());
//
//            // Insere o livro na tabela
//            db.insert(DataBaseContance.Book.TABLE_NAME, null, values);
//        }
//    }
//
//}
