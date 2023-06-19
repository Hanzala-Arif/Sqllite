package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="Userdata";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
String qry="create table Users (id integer primary key autoincrement,userid text,name text,password text)";
sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
onCreate(sqLiteDatabase);
    }
    public String adddata(String d1,String d2,String d3){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Cnic",d1);
        cv.put("Name",d2);
        cv.put("Password",d3);
        long res=db.insert("Users",null,cv);
        if(res==-1){
            return "Failed";
        }
        else{
            return "Done";
        }
    }

}
