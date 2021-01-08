package com.klasscode.depansmwen.Model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "depansMwen.db";

    //Account table name
    private static final String TABLE_ACCOUNT = "account";

    //Account table Columns names
    private static final String KEY_ID = "id";
    private static final String ID_USER = "idUser";
    private static final String BANK_NAME = "bankName";
    private static final String NUMBER_ACCOUNT = "numberAccount";
    private static final String BALANCE = "balance";
    private static final String IS_ACTIVE = "isActive";
    private static final String CREATE_AT = "createAt";
    private static final String UPDATE_AT = "updateAt";

    //Transaction table name
    private static final String TABLE_TRANSACTION = "Transac";

    //Transaction table Columns names
    private static final String KEY_IDT = "id";
    private static final String ID_ACCOUNT = "idAccount";
    private static final String TYPE = "type";
    private static final String NUMBER_TRANSFER_ACCOUNT = "numberTransferAccount";
    private static final String AMOUNT = "amount";
    private static final String CREATE_ATT = "createAt";
    private static final String UPDATE_ATT = "updateAt";

    public DataBaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user_table= "CREATE TABLE user (" +
                "id integer primary key autoincrement," +
                "username text not null," +
                "pseudo text not null unique," +
                "email text not null," +
                "password text not null," +
                "create_at datetime DEFAULT NULL," +
                "update_at datetime DEFAULT NULL " +
                ")";

        String create_table_account = "CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_USER + " INTEGER NOT NULL,"
                + BANK_NAME + " TEXT NOT NULL,"
                + NUMBER_ACCOUNT + " LONG NOT NULL,"
                + BALANCE + " LONG,"
                + IS_ACTIVE + " TEXT,"
                + CREATE_AT + " date,"
                + UPDATE_AT + " date"
                + ")";

        String create_table_transaction = "CREATE TABLE "+ TABLE_TRANSACTION +"("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_ACCOUNT + " INTEGER NOT NULL,"
                + TYPE + " TEXT,"
                + NUMBER_TRANSFER_ACCOUNT + " LONG,"
                + AMOUNT + " LONG,"
                + CREATE_AT + " date,"
                + UPDATE_AT + " date"
                +")";

        db.execSQL(create_table_transaction);
        db.execSQL(create_user_table);
        db.execSQL(create_table_account);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "user");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
    }
}
