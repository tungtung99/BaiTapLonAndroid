package com.example.tuvungtienganh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperChuDe extends SQLiteOpenHelper {
    private static final String DBName="DbAppTuVung";
    private static  final int VERSION=1;
    private static final String TABLE_NAME="ChuDe";
    private static final String IDCHUDE="_idchude";
    private static final String NAME="name";
    private static final String ANH="anh";
    private SQLiteDatabase myDB;

    public DBHelperChuDe( Context context) {
        super(context, DBName, null, VERSION);
    }

    public static String getIDCHUDE() {
        return IDCHUDE;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getANH() {
        return ANH;
    }

    public DBHelperChuDe(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TABLE_NAME +
                "( " + IDCHUDE + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                ANH + " BLOB NOT NULL" + ")";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDB(){
        myDB = getWritableDatabase();
    }
    public void closeDB(){
        if(myDB != null && myDB.isOpen()){
            myDB.close();
        }
    }

    public void createTable()
    {
        String queryTable = "CREATE TABLE " + TABLE_NAME +
                "( " + IDCHUDE + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                ANH + " BLOB NOT NULL" + ")";
        myDB.execSQL(queryTable);
    }

    public long Insert(int idchude, String name, byte[] anh){
        ContentValues values = new ContentValues();
        values.put(IDCHUDE, idchude);
        values.put(NAME, name);
        values.put(ANH, anh);
        return myDB.insert(TABLE_NAME, null, values);
    }
    public long Update(int idchude, String name, byte[] anh){
        ContentValues values = new ContentValues();
        values.put(IDCHUDE, idchude);
        values.put(NAME, name);
        values.put(ANH, anh);
        String where = IDCHUDE + " = " + idchude;
        return myDB.update(TABLE_NAME, values, where, null);
    }
    public long Delete(int idchude){
        ContentValues values = new ContentValues();
        values.put(IDCHUDE, idchude);
        String where = IDCHUDE + " = " + idchude;
        return myDB.delete(TABLE_NAME, where, null);
    }
    public Cursor ALLRecord(String query){
        return myDB.rawQuery(query, null);
    }
}
