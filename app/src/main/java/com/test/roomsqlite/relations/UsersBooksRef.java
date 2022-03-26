package com.test.roomsqlite.relations;

// https://youtu.be/AHn5JQVlJJM?t=243 (School + All students that go to this school)
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "UsersBooksRef", primaryKeys = {"uid", "bookId"})
public class UsersBooksRef {
    public UsersBooksRef(int uid, int bookId){
        this.uid = uid;
        this.bookId = bookId;
    }
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "bookId")
    private int bookId;

    public int getUid() {
        return uid;
    }

    public int getBookId() {
        return bookId;
    }
}
