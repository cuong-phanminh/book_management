package com.example.bookmanagement;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface BookDao {
    @Query("SELECT * from book")
    List<Book> getAll();

    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);
}