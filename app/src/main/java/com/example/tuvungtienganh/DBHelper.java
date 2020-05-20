package com.example.tuvungtienganh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName="DbAppTuVung";
    private static  final int VERSION=1;
    private static final String TABLE_NAWE="TuVung";
    private static final String ID="_id";
    private static final String TU="tu";
    private static final String NGHIA="nghia";
    private static final String VIDU="vidu";
    private static final String ANH="anh";
    private SQLiteDatabase myDB;

    public DBHelper(Context context){
        super(context,DBName,null,VERSION);
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

    public static String getANH() {
        return ANH;
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable="CREATE TABLE " + TABLE_NAWE +
                "( " +
                ID + " INTEGER PRIMARY KEY, " +
                TU + " TEXT NOT NULL, " +
                NGHIA+ " TEXT NOT NULL, " +
                VIDU+ " TEXT NOT NULL, " +
                ANH +" TEXT NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDB(){
        myDB=getWritableDatabase();
    }
    public void closeDB(){
        if (myDB!=null&&myDB.isOpen()){
            myDB.close();
        }
    }
    public long Insert(int id,String tu,String nghia,String vidu,String anh){
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(TU,tu);
        values.put(NGHIA,nghia);
        values.put(VIDU,vidu);
        values.put(ANH,anh);
        return myDB.insert(TABLE_NAWE,null,values);
    }
    public long Update(int id,String tu,String nghia,String vidu,String anh){
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(TU,tu);
        values.put(NGHIA,nghia);
        values.put(VIDU,vidu);
        values.put(ANH,anh);
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAWE,values,where,null);
    }
    public long Delete(int id){
        String where=ID + " = " + id;
        return myDB.delete(TABLE_NAWE,where,null);
    }
    public Cursor getAllRecord() {
        try{
            String query = "SELECT * FROM " + TABLE_NAWE ;
            return myDB.rawQuery(query, null);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
