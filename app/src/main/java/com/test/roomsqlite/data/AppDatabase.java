package com.test.roomsqlite.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.test.roomsqlite.relations.UsersBooksRef;

@Database(entities = {User.class, Book.class, UsersBooksRef.class}, version = 12)
public abstract class AppDatabase extends RoomDatabase {
    public static final String USERS_TBL = "users";
    public static final String BOOKS_TBL = "books";
    public static final String USERS_BOOK_REF = "UsersBooksRef";
    public abstract UserDao userDao();
    public abstract BookDao bookDao();
    public abstract UsersBookDao usersBookDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context, String tableName){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, tableName).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        return INSTANCE;
    }
}

