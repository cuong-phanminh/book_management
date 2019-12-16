package com.example.bookmanagement;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int bookID;
    private String bookName;
    private int releaseYear;

    public Book() {

    }

    //Hàm tạo 2 đối số
    public Book(String name, int rYear) {
        this.bookName = name;
        this.releaseYear = rYear;
    }
    //Phương thức get, set

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int rYear) {
        this.releaseYear = rYear;
    }
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    //Trả về thông tin sinh viên
    public String toString() {
        return " Name:" + this.bookName+ " ReleaseYear:" + this.releaseYear;
    }


}

