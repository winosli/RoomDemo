package com.test.roomsqlite.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int bookId;

    @ColumnInfo(name = "userID")
    public int userID;

    @ColumnInfo(name = "name")
    public String name;

    public int getBookId() {
        return bookId;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }
}
