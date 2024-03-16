package com.example.databasesql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String name = null;
    private static String DATABASE_TABLE;
    private static String col_2;
    private static String col_3;
    private static String col_4;
    private static String col_1;

    public Database(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, EMAIL TEXT, PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String nom, String mail, String phone) {
        SQLiteDatabase dB = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(col_2, nom);
        cn.put(col_3, mail);
        cn.put(col_4, phone);
        long resultat = dB.insert(DATABASE_TABLE, null, cn);
        if (resultat == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase dB = this.getWritableDatabase();
        Cursor resultat = dB.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        return resultat;
    }

    public static void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, col_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public static void update(String nom, String mail, String phone, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(col_2, nom);
        cVals.put(col_3, mail);
        cVals.put(col_4, phone);
        int count = db.update(DATABASE_TABLE, cVals, col_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


}
