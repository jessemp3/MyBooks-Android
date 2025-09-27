package com.nativo.mybooks.ui.helper;

public final  class DataBaseContance {

    private DataBaseContance() {

    }

    public static final class Book {
        public static final String TABLE_NAME = "Book";
        public static final class Columns {
            public static final String ID = "Id";
            public static final String TITLE = "Title";
            public static final String AUTHOR = "Author";
            public static final String FAVORITE = "Favorite";
            public static final String GENRE = "Genre";
        }
    }
}
