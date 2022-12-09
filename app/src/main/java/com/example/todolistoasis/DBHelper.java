package com.example.todolistoasis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "(" +
                Params.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Params.USER_NAME + " TEXT, " + Params.USER_NUMBER + " TEXT" + ")";

        sqLiteDatabase.execSQL(create);
        Log.d("dbadd", "success to create a query !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUserDetail(Model detail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.USER_NAME, detail.getName());
        values.put(Params.USER_NUMBER, detail.getNumber());

        sqLiteDatabase.insert(Params.TABLE_NAME, null, values);
        Log.d("dbadd", "success to add details !");
        sqLiteDatabase.close();
    }

    public List<Model> getAllDetails(){
        List<Model> userDetail = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do{
                Model udetail = new Model();
                udetail.setName(cursor.getString(1));
                udetail.setNumber(cursor.getString(2));

                userDetail.add(udetail);

            }while(cursor.moveToNext());
        }
        return userDetail;
    }
}
