package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //数据库名
    private static final String DATABASE_NAME = "user.db";
    //表名
    private static final String TABLE_NAME = "user";
    //行键
    private static final String COLUMN_ID = "_id";
    //名字
    private static final String COLUMN_NAME = "name";
    //邮箱地址
    private static final String COLUMN_EMIL = "emil";
    private static final String COLUMN_PASSWORD1 = "password1";
    private static final String COLUMN_PASSWORD2 = "password2";
    //性别
    private static final String COLUMN_SEX = "sex";
    //兴趣爱好
    private static final String COLUMN_HOBBY = "hobby";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //用于创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMIL + " TEXT," +
                COLUMN_PASSWORD1 + " TEXT,"+
                COLUMN_PASSWORD2 + " TEXT,"+
                COLUMN_SEX + " CHAR(1),"+
                COLUMN_HOBBY + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //插入数据
    public boolean insertUser(String name,String emil,String password1,String password2, String sex,String hobby) {
        db = getWritableDatabase();
        /**
         * 是一个键值对存储对象，主要用于在 SQLite 数据库中进行数据的插入和更新操作。它可以存储多个键值对，
         * 其中每个键都对应着一列名，每个值对应着相应列的值。
         * 常见的用法是在执行 SQL 中的 INSERT 和 UPDATE 操作时作为参数传递给相应的方法。
         */
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMIL, emil);
        values.put(COLUMN_PASSWORD1, password1);
        values.put(COLUMN_PASSWORD2, password2);
        values.put(COLUMN_SEX, sex);
        values.put(COLUMN_HOBBY, hobby);
        //可以判断是否插入成功
        boolean result = db.insert(TABLE_NAME, null, values) != -1;
        db.close();
        return result;
    }

    /**
     * @param password
     * query( table, columns, selection, selectionArgs, groupBy, having, orderBy, limit );
     *                 table：表名。
     *                 columns：要查询出来的列名。
     *                 selection：查询条件子句。
     *                 selectionArgs：对应于selection语句中占位符的值。
     *                 groupBy：分组。相当于select语句group by关键字后面的部分。
     *                 having：分组后聚合的过滤条件。相当于select语句having关键字后面的部分。
     *                 orderBy：排序。相当于select语句order by关键字后面的部分 ASC或DESC。
     *                 limit：指定偏移量和获取的记录数。
     */
    //查询方法可以进行密码匹配实现
    public boolean validUser(String emil, String password) {
        db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMIL + "=? AND " + COLUMN_PASSWORD1 + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{emil, password});
        //用于判断是否有结果
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }

    //判别在注册时用户账号是否已经存在
    public boolean validEmil(String emil) {
        db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMIL + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{emil});
        //用于判断是否有结果
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }
}