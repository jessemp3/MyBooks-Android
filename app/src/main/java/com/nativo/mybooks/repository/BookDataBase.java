package com.nativo.mybooks.repository;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nativo.mybooks.entity.BookEntity;

@Database(entities = {BookEntity.class}, version = 1)
public abstract class BookDataBase extends RoomDatabase {
    private static final String NAME = "book_database";
    private static BookDataBase instace;

    public abstract BookDAO bookDAO();//assim o dao e o db se conectam

    public static BookDataBase getInstance(Context context) {
        synchronized ( BookDataBase.class) {
            if (instace == null) {
                instace = Room.databaseBuilder(context, BookDataBase.class, NAME)
                        .addCallback(MyBooksDBCallBack.creation)
                        .addMigrations(Migrations.migration_1_2)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instace;
    }

    private static class MyBooksDBCallBack {
        private static final RoomDatabase.Callback creation = new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
    }

    private static class Migrations{
        private static final Migration migration_1_2 = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase db) {
                super.migrate(db);
            }
        };
    }
}
