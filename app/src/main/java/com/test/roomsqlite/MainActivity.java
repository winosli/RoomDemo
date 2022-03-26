package com.test.roomsqlite;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.test.roomsqlite.adapters.UserAdapter;
import com.test.roomsqlite.data.Book;
import com.test.roomsqlite.data.BookDao;
import com.test.roomsqlite.data.User;
import com.test.roomsqlite.data.AppDatabase;
import com.test.roomsqlite.data.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.onUserListener {

    private RecyclerView rvMain;
    private UserAdapter adapter;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private UserDao userDao;
    private AppDatabase db;
    private List<User> usersList;
    private EditText etSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        searchField();

        forResultRefresh();

        displayAllUsers();
        rvListener();

     //   addBooksAndUsersForTheFirstTime();
    }

    private void searchField() {
        etSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
    }

    private void addBooksAndUsersForTheFirstTime() {
        AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext(), AppDatabase.BOOKS_TBL);
        BookDao bookDao = db.bookDao();

        Book book1 = new Book();
        book1.name = "Good Night";
        Book book2 = new Book();
        book2.name = "Meow";
        Book book3 = new Book();
        book3.name = "Spiderman";
        bookDao.addBook(book3);
        Book book4 = new Book();
        book4.name = "Games";
        bookDao.addBook(book4);

        // Users
        AppDatabase dbUsers  = AppDatabase.getDbInstance(getApplicationContext(), AppDatabase.USERS_TBL);
        UserDao usersDao = dbUsers.userDao();

        User user = new User();
        user.firstName = "Moshe";
        user.lastName = "Levy";
        usersDao.addUser(user);

        User user2 = new User();
        user2.firstName = "Yossi";
        user2.lastName = "Cohen";
        usersDao.addUser(user2);

        User user3 = new User();
        user3.firstName = "Steve";
        user3.lastName = "Amzaleg";
        usersDao.addUser(user3);

        adapter.notifyDataSetChanged();
    }

    private void rvListener() {
//        rvMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int itemPosition = rvMain.getChildLayoutPosition(view);
//                User item = usersList.get(itemPosition);
//                Toast.makeText(MainActivity.this, item.getFirstName() + " " + item.getLastName(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void initializeViews() {
        etSearchField = findViewById(R.id.etSearchField);
        db = AppDatabase.getDbInstance(this.getApplicationContext(), AppDatabase.USERS_TBL);
        userDao = db.userDao();
        rvMain = findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvMain.addItemDecoration(dividerItemDecoration);
    }

    private void forResultRefresh() {
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        displayAllUsers();
                    }
                });
    }

    private void displayAllUsers() {
        usersList = userDao.getAll();
        adapter = new UserAdapter(MainActivity.this, usersList, this);
        rvMain.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_user:
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                someActivityResultLauncher.launch(intent);
                return true;
            case R.id.menu_add_book:
                Intent intentBook = new Intent(MainActivity.this, BooksActivity.class);
                someActivityResultLauncher.launch(intentBook);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onUserClick(int position) {
        User user = usersList.get(position);
//        Intent intent = new Intent(MainActivity.this, BooksActivity.class);
//        intent.putExtra("userID", user.getUid());
//        startActivity(intent);

        Intent intent = new Intent(MainActivity.this, BooksActivity.class);
        intent.putExtra("user",user);
        someActivityResultLauncher.launch(intent);

      //  Toast.makeText(MainActivity.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
    }

    void filter(String text){
        List<User> temp = new ArrayList();
        for(User d: usersList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if((d.getFirstName() + d.getLastName()).contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }



}