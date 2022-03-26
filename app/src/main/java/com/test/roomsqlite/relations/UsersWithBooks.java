package com.test.roomsqlite.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.test.roomsqlite.data.User;

import java.util.List;

public class UsersWithBooks {
    @Embedded
    User user;

    @Relation(
            parentColumn = "uid",
            entityColumn = "bookId",
            associateBy = @Junction(
                    value = UsersBooksRef.class
                    )
    )
    List<User> userList;

}
