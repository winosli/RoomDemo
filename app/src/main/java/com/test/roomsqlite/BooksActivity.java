package com.test.roomsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.test.roomsqlite.adapters.BookAdapter;
import com.test.roomsqlite.data.AppDatabase;
import com.test.roomsqlite.data.Book;
import com.test.roomsqlite.data.BookDao;
import com.test.roomsqlite.data.User;
import com.test.roomsqlite.data.UserDao;
import com.test.roomsqlite.data.UsersBookDao;
import com.test.roomsqlite.relations.UsersBooksRef;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BooksActivity extends AppCompatActivity implements BookAdapter.onBookListener {

    private RecyclerView recyclerView;
    private List<Book> list;
    private BookDao bookDao;
    private AppDatabase db;
    private BookAdapter bookAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        initializeViews();
    }

    private void sort(){
        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return 0;
            }
        });
//        Collections.sort(arrayList, new Comparator<Movie>() {
//            @Override
//            public int compare(Movie lhs, Movie rhs) {
//                return (""+rhs.isFavorite()).compareTo("" + lhs.isFavorite());
//            }
//        });
    }

    private void initializeViews() {
        user = (User) getIntent().getSerializableExtra("user");
        db = AppDatabase.getDbInstance(this.getApplicationContext(), AppDatabase.BOOKS_TBL);
        bookDao = db.bookDao();
        recyclerView = findViewById(R.id.rvBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 2 Columns
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        list = bookDao.getAll();
        bookAdapter = new BookAdapter(BooksActivity.this, list, this);
        recyclerView.setAdapter(bookAdapter);
        int a = 54;
    }

    @Override
    public void onBookClick(int position) {
        Book book = list.get(position);
        //AppDatabase databaseUsers = AppDatabase.getDbInstance(this.getApplicationContext(), AppDatabase.USERS_BOOK_REF);
        db = AppDatabase.getDbInstance(this.getApplicationContext(), AppDatabase.USERS_BOOK_REF);
//        UserDao userDao = databaseUsers.userDao();
//        User user = userDao.findByID(userID);
//        userDao.addBookToUser(book.userID + "");
        UsersBookDao usersBookDao = db.usersBookDao();
        usersBookDao.addRef(new UsersBooksRef(user.getUid(), book.getBookId()));
      //  bookDao.updateBookUserID(book.getBookId(), user.getUid());

        if(user != null) {
            Toast.makeText(getBaseContext(), book.getName() + " is now connected to " + user.getFirstName(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), book.getName() + " is now connected to " + user.getUid(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}