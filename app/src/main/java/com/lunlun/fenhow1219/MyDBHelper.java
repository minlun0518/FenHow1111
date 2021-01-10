package com.lunlun.fenhow1219;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String database = "hospital.db"; //資料庫名稱
    private static final int version = 1; //資料庫版本

    private static final String SQL_CREATE_LOCAL_USER = "CREATE TABLE IF NOT EXISTS loclUser (id INTEGER PRIMARY KEY AUTOINCREMENT,userNo VARCHAR(10) NOT NULL,userName VARCHAR(10) NOT NULL,chartNo TEXT )";

    private static final String SQL_CREATE_USER_IMEI ="CREATE TABLE IF NOT EXISTS UserImei(id INTEGER PRIMARY KEY AUTOINCREMENT,employee_id int(11) NOT NULL,device TEXT NOT NULL)";

    private static final String SQL_INSERT_USER_IMEI1 = " INSERT INTO UserImei('employee_id','device') VALUES (708,'357798080499328')" ;
    private static final String SQL_INSERT_USER_IMEI2 = " INSERT INTO UserImei('employee_id','device') VALUES (708,'456789838743874')" ;
    private static final String SQL_INSERT_USER_IMEI3 = " INSERT INTO UserImei('employee_id','device') VALUES (708,'348930505404894')" ;

    private static final String SQL_QUERY_USER_IMEI = " SELECT * FORM UserImei " ;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //自建的建構子,只需傳入一個Context物件即可
    public MyDBHelper(Context context) {
        this(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_LOCAL_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_IMEI);
//        sqLiteDatabase.execSQL("CREATE TABLE department (" +
//                "  department_id int(11) NOT NULL," +
//                "  department_name varchar(50) NOT NULL" +
//                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

        sqLiteDatabase.execSQL(SQL_INSERT_USER_IMEI1);
        sqLiteDatabase.execSQL(SQL_INSERT_USER_IMEI2);
        sqLiteDatabase.execSQL(SQL_INSERT_USER_IMEI3);

    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
