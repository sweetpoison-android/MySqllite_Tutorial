package com.example.mysqllite_tutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlliteDatabase_holder extends SQLiteOpenHelper {

    public static final String databse_name = "Login_database";

    public SqlliteDatabase_holder( Context context) {
        super(context, databse_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table UserAccount (id integer primary key autoincrement, name text, email text, password text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists UserAccount");

    }

    //   insert data in database

    public Boolean insertData(String Uname, String Uemail, String Upassword)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",Uname);
        cv.put("email",Uemail);
        cv.put("password",Upassword);

        long result = mydb.insert("UserAccount", null, cv);
        if (result == -1)                  //  data not insert in database
        {
          return false;
        }
        else return true;     //  data inserted in database
    }

    //  check email of user from database

    public Boolean checkUseremail(String email)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from UserAccount where email = ? ", new String[] {email});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkUseremailandpassword(String email, String password)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from UserAccount where email = ? and password = ? ", new String[] {email, password});
        if (cursor.getCount() > 0) return true;

        else return false;
    }
}
