package com.trubnikov.numerology_person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {
	private static final String TAG = "myNumLog";
  
  private static final String DB_NAME = "Journal.db";
  private static final int DB_VERSION = 1;
  private static final String DB_TABLE = "myJournal";
  
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_SUR = "surname";
  public static final String COLUMN_NAM = "name";
  public static final String COLUMN_PAT = "patronymics";
  public static final String COLUMN_BD = "birthday";
  public static final String COLUMN_BM = "birthmonth";
  public static final String COLUMN_BY = "birthyear";
  
  private static final String DB_CREATE = 
    "create table if not exists " + DB_TABLE + 
    "(" + COLUMN_ID + " integer primary key autoincrement, " +
      COLUMN_SUR + " text, " +
      COLUMN_NAM + " text, " +
      COLUMN_PAT + " text, " +
      COLUMN_BD + " integer, " +
      COLUMN_BM + " integer, " +
      COLUMN_BY + " integer" +
    ");";
  
  private final Context mCtx;
  
  
  private DBHelper mDBHelper;
  private SQLiteDatabase mDB;
  
  public DB(Context ctx) {
    mCtx = ctx;
  }
  
  // открыть подключение
  public void open() {
    mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
    mDB = mDBHelper.getWritableDatabase();
  }
  
  // закрыть подключение
  public void close() {
    if (mDBHelper!=null) mDBHelper.close();
  }
  
  // получить все данные из таблицы DB_TABLE
  public Cursor getAllData() {
    return mDB.query(DB_TABLE, null, null, null, null, null, "surname");
  }
  
  // получить данные строки из таблицы DB_TABLE 
  public Cursor getDataRow(long id) {
    return mDB.query(DB_TABLE, null, COLUMN_ID + " = " + id, null, null, null, null);
  }
  
  // добавить запись в DB_TABLE
  public void addRec(String sur, String nam, String pat, int bd, int bm, int by) {
    ContentValues cv = new ContentValues();
    cv.put(COLUMN_SUR, sur);
    cv.put(COLUMN_NAM, nam);
    cv.put(COLUMN_PAT, pat); 
    cv.put(COLUMN_BD, bd);
    cv.put(COLUMN_BM, bm);
    cv.put(COLUMN_BY, by);
    mDB.insert(DB_TABLE, null, cv);
  }
  
  // изменить запись в DB_TABLE
  public void editRec(String sur, String nam, String pat, int bd, int bm, int by, long id) {
		Log.d(TAG, "IdEd= "+id);
		Log.d(TAG, "surEd= "+sur);
		Log.d(TAG, "nameEd= "+nam);
		Log.d(TAG, "patEd= "+pat);
		Log.d(TAG, "birthdayEd= "+bd);
		Log.d(TAG, "birthmonthEd= "+bm);
		Log.d(TAG, "birthyearEd= "+by);
    ContentValues cv = new ContentValues();
    cv.put(COLUMN_SUR, sur);
    cv.put(COLUMN_NAM, nam);
    cv.put(COLUMN_PAT, pat); 
    cv.put(COLUMN_BD, bd);
    cv.put(COLUMN_BM, bm);
    cv.put(COLUMN_BY, by);
    mDB.update(DB_TABLE, cv, COLUMN_ID + " = " + id, null);
  }
  
  // удалить запись из DB_TABLE
  public void delRec(long id) {
    mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
  }
  
  // класс по созданию и управлению Ѕƒ
  private class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, CursorFactory factory,
        int version) {
      super(context, name, factory, version);
    }

    // создаем и заполн€ем Ѕƒ
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
  }
}
