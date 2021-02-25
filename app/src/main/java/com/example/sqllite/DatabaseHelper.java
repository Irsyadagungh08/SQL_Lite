package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1 ;
    private static final String DB_NAME = "Memo";
    private static final String TABLE_NAME = "tbl_memo";
    private static final String KEY_JUDUL = "judul";
    private static final String KEY_DESK = "deskripsi";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createUserTable="Create Table "+TABLE_NAME+"("+KEY_JUDUL+" TEXT PRIMARY KEY,"+KEY_DESK+" STRING "+")";
        db.execSQL(createUserTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }
    public void insert(Data data){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_JUDUL,data.getJudul());
        values.put(KEY_DESK,data.getDeskripsi());
        db.insert(TABLE_NAME,null,values);
    }

    public List<Data> selectUserData(){
        ArrayList<Data> userList=new ArrayList<Data>();
        SQLiteDatabase db= getReadableDatabase();
        String[] columns={KEY_JUDUL,KEY_DESK};
        Cursor c =db.query(TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext()){
            String judul=c.getString(0);
            String desk=c.getString(1);
            Data personBean=new Data();
            personBean.setJudul(judul);
            personBean.setDeskripsi(desk);
            userList.add(personBean);
        }
        return userList;
    }
    public void delete(String name){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_JUDUL+"='"+name+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }
    public void update(Data personBean){
        SQLiteDatabase db=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DESK,personBean.getDeskripsi());
        String whereClause=KEY_JUDUL+"='"+personBean.getJudul()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }

}
