package com.example.manasi.textinsert3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manasi on 15-10-2017.
 */

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context){
        super(context, "Textimage32.db", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE ALBUM(MSG TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS ALBUM ");
        onCreate(db);
    }
    public void insert_album(String Song)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("MSG",Song);
        db.insert("ALBUM",null,contentvalues);
    }

    public Cursor list_album()
    {
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM ALBUM",null);
        return cursor;
    }

    public void deleteEntry(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "MSG=?";
        int numberOFEntriesDeleted = db.delete("ALBUM", where, new String[]{text});
    }

    public Cursor getSingleEntry(String text)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query("ALBUM", null, " MSG=?", new String[]{text}, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public void  updateEntry(String oldText,String newText)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("MSG", newText);

        String where="MSG = ?";
        db.update("ALBUM",updatedValues, where, new String[]{oldText});
    }
}
