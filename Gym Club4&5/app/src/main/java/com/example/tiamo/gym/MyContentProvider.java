package com.example.tiamo.gym;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private WifiSsidDatabaseHelper dbOpenHelper;
    private static final int trainercode =1;
    private static final int newcode=2;

    //数据库
    public static final String AUTOHORITY = "myprovider";//权限

    private static final String DATABASE_NAME = "gym.db";//数据库名称
    public static final String TABLE_TRAINERINFO = "table_trainerinfo";//用户表和课程表
    public static final String TABLE_NEW= "table_new";

    private static final UriMatcher mMatcher;
    static{
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUTOHORITY,"table_trainerinfo", trainercode);
        mMatcher.addURI(AUTOHORITY, "table_new", newcode);
    }
//创建数据库，只执行一次，如果创建了就不会再执行

    @Override
    public boolean onCreate() {
        dbOpenHelper=new WifiSsidDatabaseHelper(getContext(),DATABASE_NAME,null,1);
        dbOpenHelper.getReadableDatabase();
        dbOpenHelper.getWritableDatabase();
        return false;
    }
    //查询数据库
    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] selectionArgs, String sortOrder) {
        String table=getTableName(uri);
        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        return db.query(table, projection, where, selectionArgs, null, null, sortOrder, null);
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }
    //插入数据
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table=getTableName(uri);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        db.insert(table, "_id", values);
        return null;
    }
    //删除数据
    @Override
    public int delete(Uri uri, String where, String[] selectionArgs) {
        String table=getTableName(uri);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        return db.delete(table, where, selectionArgs);
    }
    //修改数据
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
        String table=getTableName(uri);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        return db.update(table, values, where, selectionArgs);
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case trainercode:
                tableName = TABLE_TRAINERINFO;
                break;
            case newcode:
                tableName = TABLE_NEW;
                break;
        }
        return tableName;
    }

    public class WifiSsidDatabaseHelper extends SQLiteOpenHelper {
        private static final String CRATE_TABLE_TRAINERINFO_SQL =
                "create table "
                        + TABLE_TRAINERINFO
                        +"(name VARCHAR(50) primary key,age VARCHAR(50),sex VARCHAR(50),info VARCHAR(50))";
        private static final String CRATE_TABLE_NEW_SQL =
                "create table "
                        +TABLE_NEW
                        +"(title VARCHAR(50) primary key,content VARCHAR(50),time VARCHAR(50))";
        public WifiSsidDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CRATE_TABLE_TRAINERINFO_SQL);
            db.execSQL(CRATE_TABLE_NEW_SQL);
        }
        //版本更新
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


