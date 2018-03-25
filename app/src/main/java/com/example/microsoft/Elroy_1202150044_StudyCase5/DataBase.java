package com.example.microsoft.Elroy_1202150044_StudyCase5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Elroy Yehezkiel S
 */

public class DataBase extends SQLiteOpenHelper {



    //database yang digunakan
    private static final int DATABASE_VERSION = 2;

    //Deklarasi nama DataBase
    static final String db_name = "studycase5.db";

    //Deklarasi nama table menggunakan SQLite
    public static final String tbl_name = "TodoList";

    //Deklarasi kolom yang ada pada di dalam Tabel
    public static final String col_1 = "id";
    public static final String col_2 = "name";
    public static final String col_3 = "desc";
    public static final String col_4 = "priority";

    //Konstruktor class DataBase
    public DataBase(Context context) {

        super(context, db_name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Mambuat tabel dan kolom pada DataBase
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + tbl_name + " (" +
                col_1 + " INTEGER PRIMARY KEY autoincrement, " +
                col_2 + " TEXT NOT NULL, " +
               col_3 + " TEXT NOT NULL," +
                col_4 + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    //Method untuk melakukan perubahan/upgarade database yang dimilii
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +tbl_name);
        onCreate(db);
    }

    //Method untuk mengakses dan melihat DataBase
    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " +tbl_name;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(col_1, cursor.getString(0));
                map.put(col_2, cursor.getString(1));
                map.put(col_3, cursor.getString(2));
                map.put(col_4, cursor.getString(3));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" + wordList);
        database.close();
        return wordList;
    }

    //Method untuk menginputkan data ke DataBase
    public void insert(String name, String address, String jumlah) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + tbl_name + " (name, desc, priority) " +
                "VALUES ('" + name + "', '" + address + "','" + jumlah + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    //Method untuk menghapus data di DataBase
    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " +tbl_name + " WHERE " + col_1+ "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
