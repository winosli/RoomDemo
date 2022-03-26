package com.test.roomsqlite.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.test.roomsqlite.relations.UsersBooksRef;

import java.util.List;

@Dao
public interface UsersBookDao {
    @Query("SELECT * FROM UsersBooksRef")
    List<UsersBooksRef> getAll();

    @Query("SELECT * FROM UsersBooksRef WHERE uid=:uid")
    List<UsersBooksRef> getAllByUserId(int uid);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addRef(UsersBooksRef ref);

    @Query("UPDATE UsersBooksRef SET uid=:uid WHERE bookId=:bookId")
    void updateRef(int uid, int bookId);

    @Insert
    void insertAll(UsersBooksRef... UsersBooksRef);

    @Delete
    void delete(UsersBooksRef ref);
}
