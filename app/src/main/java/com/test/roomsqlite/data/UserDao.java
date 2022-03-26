package com.test.roomsqlite.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByBookIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND last_name LIKE :last")
    User findByName(String first, String last);

    @Query("SELECT * FROM users WHERE uid LIKE :ID")
    User findByID(int ID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

}
