package com.test.roomsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.roomsqlite.data.AppDatabase;
import com.test.roomsqlite.data.User;
import com.test.roomsqlite.data.UserDao;

public class AddUserActivity extends AppCompatActivity {

    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initializeViews();

        addListener();
    }

    private void initializeViews() {
        btnAddUser = findViewById(R.id.btnAddUser);
    }

    private void addListener() {
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etFirst = findViewById(R.id.etFirstNameAddUser);
                EditText etLast = findViewById(R.id.etLastNameAddUser);
                String firstName = etFirst.getText().toString();
                String lastName = etLast.getText().toString();
                if(firstName.length() >= 2 && lastName.length() >= 2){
                    AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext(), AppDatabase.USERS_TBL);
                    UserDao userDao = db.userDao();

                    User user = new User();
                    // user1.uid = 1;
                    user.firstName = firstName;
                    user.lastName = lastName;
                    userDao.addUser(user);
                    Toast.makeText(AddUserActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddUserActivity.this, "Add at least 2 characters in first name and last name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}