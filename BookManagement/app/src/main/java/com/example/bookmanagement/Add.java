package com.example.bookmanagement;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class Add extends AppCompatActivity {
    Button btnAddBook;
    EditText et1, et2;
    public BookDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btnAddBook = findViewById(R.id.btnAddBook);


        db = Room.databaseBuilder(getApplicationContext(),
                BookDatabase.class, "database-name").build();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                addBook();
                finish();
            }
        });


    }

    public void addBook() {
        final Book book = new Book(et1.getText().toString(), Integer.parseInt(et2.getText().toString()));
//        book.setBookName(et1.getText().toString());
//        book.setReleaseYear(Integer.parseInt(et2.getText().toString()));
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                db.bookDao().insert(book);
                return null;
            }
        }.execute();
    }
}
