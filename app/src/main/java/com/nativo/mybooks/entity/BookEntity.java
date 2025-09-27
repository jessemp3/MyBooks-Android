package com.nativo.mybooks.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private final int id;
    @ColumnInfo
    private final String title;
    @ColumnInfo
    private final String author;
    @ColumnInfo
    private  boolean favorite;
    @ColumnInfo
    private final String  genre;


    public BookEntity(int id, String title, String author, boolean favorite, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.favorite = favorite;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

}
