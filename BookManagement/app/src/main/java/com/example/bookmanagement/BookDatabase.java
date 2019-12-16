package com.example.bookmanagement;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 4)
public abstract class BookDatabase extends RoomDatabase {
    abstract BookDao bookDao();


}
