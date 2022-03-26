package com.test.roomsqlite.data;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.test.roomsqlite.relations.UsersBooksRef;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UsersBookDao_Impl implements UsersBookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UsersBooksRef> __insertionAdapterOfUsersBooksRef;

  private final EntityInsertionAdapter<UsersBooksRef> __insertionAdapterOfUsersBooksRef_1;

  private final EntityDeletionOrUpdateAdapter<UsersBooksRef> __deletionAdapterOfUsersBooksRef;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRef;

  public UsersBookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsersBooksRef = new EntityInsertionAdapter<UsersBooksRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `UsersBooksRef` (`uid`,`bookId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UsersBooksRef value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getBookId());
      }
    };
    this.__insertionAdapterOfUsersBooksRef_1 = new EntityInsertionAdapter<UsersBooksRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `UsersBooksRef` (`uid`,`bookId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UsersBooksRef value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getBookId());
      }
    };
    this.__deletionAdapterOfUsersBooksRef = new EntityDeletionOrUpdateAdapter<UsersBooksRef>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `UsersBooksRef` WHERE `uid` = ? AND `bookId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UsersBooksRef value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getBookId());
      }
    };
    this.__preparedStmtOfUpdateRef = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE UsersBooksRef SET uid=? WHERE bookId=?";
        return _query;
      }
    };
  }

  @Override
  public void addRef(final UsersBooksRef ref) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsersBooksRef.insert(ref);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final UsersBooksRef... UsersBooksRef) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsersBooksRef_1.insert(UsersBooksRef);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final UsersBooksRef ref) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUsersBooksRef.handle(ref);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRef(final int uid, final int bookId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRef.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, uid);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, bookId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateRef.release(_stmt);
    }
  }

  @Override
  public List<UsersBooksRef> getAll() {
    final String _sql = "SELECT * FROM UsersBooksRef";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
      final List<UsersBooksRef> _result = new ArrayList<UsersBooksRef>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UsersBooksRef _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpBookId;
        _tmpBookId = _cursor.getInt(_cursorIndexOfBookId);
        _item = new UsersBooksRef(_tmpUid,_tmpBookId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UsersBooksRef> getAllByUserId(final int uid) {
    final String _sql = "SELECT * FROM UsersBooksRef WHERE uid=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, uid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
      final List<UsersBooksRef> _result = new ArrayList<UsersBooksRef>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UsersBooksRef _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpBookId;
        _tmpBookId = _cursor.getInt(_cursorIndexOfBookId);
        _item = new UsersBooksRef(_tmpUid,_tmpBookId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
