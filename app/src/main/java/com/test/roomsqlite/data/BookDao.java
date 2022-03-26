package com.test.roomsqlite.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    List<Book> getAll();

    @Query("SELECT * FROM books WHERE userID IN (:bookIds)")
    List<Book> loadAllByIds(int[] bookIds);

    @Query("SELECT * FROM books WHERE name LIKE :name")
    Book findByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addBook(Book book);

    @Query("UPDATE books SET userID=:userID WHERE bookId=:bookID")
    void updateBookUserID(int bookID, int userID);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);

}
