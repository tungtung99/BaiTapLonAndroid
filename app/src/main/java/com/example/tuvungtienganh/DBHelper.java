package com.example.tuvungtienganh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName="DbAppTuVung";
    private static  final int VERSION=1;
    private static final String TABLE_NAWE="TuVung1";
    private static final String ID="_id";
    private static final String IDCHUDE="idchude";
    private static final String FAV="idlove";
    private static final String TU="tu";
    private static final String NGHIA="nghia";
    private static final String VIDU="vidu";
    private static final String ANH="anh";


    private SQLiteDatabase myDB;

    public DBHelper( Context context) {
        super(context, DBName, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getTU() {
        return TU;
    }

    public static String getNGHIA() {
        return NGHIA;
    }

    public static String getVIDU() {
        return VIDU;
    }

    public static String getIDCHUDE() {
        return IDCHUDE;
    }

    public static String getANH() {
        return ANH;
    }

    public static String getFAV() {
        return FAV;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TABLE_NAWE +
                "( " + ID + " INTEGER PRIMARY KEY, " +
                IDCHUDE + " INTEGER NOT NULL, " +
                TU + " TEXT NOT NULL, " +
                NGHIA + " TEXT NOT NULL, " +
                VIDU + " TEXT NOT NULL, " +
                ANH + " BLOB NOT NULL, " +
                FAV + " INTEGER NOT NULL" + ")";
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

    public void createtable()
    {
        String queryTable = "CREATE TABLE " + TABLE_NAWE +
                "( " + ID + " INTEGER PRIMARY KEY, " +
                IDCHUDE + " INTEGER NOT NULL, " +
                TU + " TEXT NOT NULL, " +
                NGHIA + " TEXT NOT NULL, " +
                VIDU + " TEXT NOT NULL, " +
                ANH + " BLOB NOT NULL, " +
                FAV + " INTEGER NOT NULL" + ")";
        myDB.execSQL(queryTable);
    }

    public long Insert(int id, int idchude, String tu, String nghia, String vidu, byte[] anh, int fav){
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(IDCHUDE, idchude);
        values.put(TU,tu);
        values.put(NGHIA, nghia);
        values.put(VIDU, vidu);
        values.put(ANH, anh);
        values.put(FAV, fav);
        return myDB.insert(TABLE_NAWE, null, values);

    }
    public long Update(int id, int idchude, String tu, String nghia, String vidu, byte[] anh, int fav){
        ContentValues values = new ContentValues();
        values.put(TU,tu);
        values.put(IDCHUDE, idchude);
        values.put(NGHIA, nghia);
        values.put(VIDU, vidu);
        values.put(ANH, anh);
        values.put(FAV, fav);
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAWE, values, where, null);
    }
    public long UpdateIdlove(int id, int fav){
        ContentValues values = new ContentValues();
        values.put(FAV, fav);
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAWE, values, where, null);
    }
    public long Delete(int id){
        ContentValues values = new ContentValues();
        values.put(ID, id);
        String where = ID + " = " + id;
        return myDB.delete(TABLE_NAWE, where, null);
    }
    public Cursor ALLRecord(String query){
        return myDB.rawQuery(query, null);
    }
}
