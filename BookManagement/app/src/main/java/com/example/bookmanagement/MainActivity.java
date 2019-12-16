package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public abstract class MainActivity extends AppCompatActivity implements BookAdapter.OnItemClicked {
    ListView listView;
    EditText et1, et2;
    BookDatabase db;
    Button btnAdd;
    private TextView txtName,txtReleaseYear;
    RecyclerView rvBook;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                BookDatabase.class, "database-name").build();

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSecondScreen();
            }
        });

        rvBook = findViewById(R.id.rvBook);
        rvBook.setLayoutManager(new LinearLayoutManager(this));

        bookAdapter = new BookAdapter();
        bookAdapter.setOnClick(MainActivity.this);


        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                bookAdapter.data = db.bookDao().getAll();
                rvBook.setAdapter(bookAdapter);
                //Log.i("database1", "size" + bookList.size());

                return null;
            }
        }.execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAndShowData();

    }

    private void getAndShowData() {
        new AsyncTask<Void, Void, List<Book>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected List<Book> doInBackground(Void... voids) {
                List<Book> students = db.bookDao().getAll();
                return students;
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);
                bookAdapter.data = books;
                bookAdapter.notifyDataSetChanged();

            }
        }.execute();
    }

    //cach chuyen screen
    private void openSecondScreen() {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    @Override
    public void onClickDelete(final int position) {
        new AsyncTask<Void, Void, List<Book>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected List<Book> doInBackground(Void... voids) {
                db.bookDao().delete(bookAdapter.data.get(position));

                return null;
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);
                bookAdapter.notifyDataSetChanged();
                getAndShowData();
            }
        }.execute();
    }


}


