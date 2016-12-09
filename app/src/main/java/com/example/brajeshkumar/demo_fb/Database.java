package com.example.brajeshkumar.demo_fb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brajesh Kumar on 11/25/2016.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FB_DB.db";
    public static final String TABLE_NAME = "Friend_table";
    public static final String ID = "ID";
    public static final String F_NAME = "Name";
    public static final String F_Qualification = "Qualification";
    public static final String F_Ame = "Ame";
    public static final String F_GENDER = "Friend_Gender";
    public static final String F_EmailID = "EmailID";
    public static final String mImagePath = "Friend_Image";

    public Database(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Qualification TEXT,Friend_Gender TEXT,EmailID TEXT,Ame TEXT,Friend_Image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(FriendDetails wd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(F_NAME, wd.getName());
        contentValues.put(F_Qualification, wd.getQualification());
        contentValues.put(F_GENDER, wd.getGender());
        contentValues.put(F_EmailID, wd.getEmailID());
        contentValues.put(F_Ame, wd.getAme());
        contentValues.put(mImagePath, wd.getFriend_Image());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public List<FriendDetails> getAllData() {
        List<FriendDetails> res = new ArrayList<FriendDetails>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                FriendDetails wd = new FriendDetails();
                wd. setId(Integer.parseInt(cursor.getString(0)));
                wd.setName(cursor.getString(1));
                wd.setQualification(cursor.getString(2));
                wd.setGender(cursor.getString(3));
                wd.setEmailID(cursor.getString(4));
                wd.setAme(cursor.getString(5));
                wd.setFriend_Image(cursor.getString(6));
                // Adding contact to list
                res.add(wd);
            } while (cursor.moveToNext());
        }
        return res;
    }
}
