package com.test.roomsqlite.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters{
    @TypeConverter
    public String fromBookList(List<Book> bookList) {
        if (bookList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Book>>() {}.getType();
        String json = gson.toJson(bookList, type);
        return json;
    }

    @TypeConverter
    public List<Book> toBookList(String booksStr) {
        if (booksStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Book>>() {}.getType();
        List<Book> countryLangList = gson.fromJson(booksStr, type);
        return countryLangList;
    }

//    @TypeConverter
//    public List<Integer> gettingListFromString(String genreIds) {
//        List<Integer> list = new ArrayList<>();
//
//        String[] array = genreIds.split(",");
//
//        for (String s : array) {
//            if (!s.isEmpty()) {
//                list.add(Integer.parseInt(s));
//            }
//        }
//        return list;
//    }
//
//    @TypeConverter
//    public String writingStringFromList(List<Integer> list) {
//        String genreIds = "";
//        for (int i : list) {
//            genreIds += "," + i;
//        }
//        return genreIds;
//    }}
}
