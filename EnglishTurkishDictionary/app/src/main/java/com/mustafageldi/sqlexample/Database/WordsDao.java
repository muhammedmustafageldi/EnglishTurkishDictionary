package com.mustafageldi.sqlexample.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mustafageldi.sqlexample.Models.Words;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kotlinx.coroutines.selects.WhileSelectKt;

public class WordsDao {

    public ArrayList<Words> getAllWords(DataBaseHelper helper){
        ArrayList<Words> wordsArrayList = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM kelimeler",null);

        while(c.moveToNext()){

            @SuppressLint("Range") int wordId = c.getInt(c.getColumnIndex("kelime_id"));
            @SuppressLint("Range") String english = c.getString(c.getColumnIndex("ingilizce"));
            @SuppressLint("Range") String turkish = c.getString(c.getColumnIndex("turkce"));

            Words word = new Words(wordId,english,turkish);
            wordsArrayList.add(word);
        }

        c.close();
        return wordsArrayList;
    }


    public ArrayList<Words> getRandom10(DataBaseHelper helper){
        ArrayList<Words> randomList = new ArrayList<>();

        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kelimeler ORDER BY RANDOM() LIMIT 10",null);

        while (c.moveToNext()){

            @SuppressLint("Range") int wordId = c.getInt(c.getColumnIndex("kelime_id"));
            @SuppressLint("Range") String english = c.getString(c.getColumnIndex("ingilizce"));
            @SuppressLint("Range") String turkish = c.getString(c.getColumnIndex("turkce"));

            Words word = new Words(wordId,english,turkish);
            randomList.add(word);
        }
        c.close();
        return randomList;
    }

    public ArrayList<Words> getRandom2(DataBaseHelper helper, int id){
        ArrayList<Words> falseAnswer = new ArrayList<>();

        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kelimeler WHERE kelime_id !="+ id +" ORDER BY RANDOM() LIMIT 3",null);

        while (c.moveToNext()){

            @SuppressLint("Range") int wordId = c.getInt(c.getColumnIndex("kelime_id"));
            @SuppressLint("Range") String english = c.getString(c.getColumnIndex("ingilizce"));
            @SuppressLint("Range") String turkish = c.getString(c.getColumnIndex("turkce"));

            Words word = new Words(wordId,english,turkish);
            falseAnswer.add(word);
        }
        c.close();
        return falseAnswer;

    }




    public void addWord(DataBaseHelper helper, String english, String turkish){
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ingilizce",english);
        values.put("turkce",turkish);

        database.insertOrThrow("kelimeler",null,values);
        database.close();
    }



    public ArrayList<Words> searchWords(DataBaseHelper helper, String searchText){

        ArrayList<Words> handleResponseList = new ArrayList<>();

        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%"+searchText+"%'",null);

        while(c.moveToNext()){
            @SuppressLint("Range") int wordId = c.getInt(c.getColumnIndex("kelime_id"));
            @SuppressLint("Range") String english = c.getString(c.getColumnIndex("ingilizce"));
            @SuppressLint("Range") String turkish = c.getString(c.getColumnIndex("turkce"));

         Words word = new Words(wordId,english,turkish);
         handleResponseList.add(word);
        }

        c.close();
        return handleResponseList;
    }




}
