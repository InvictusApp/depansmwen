package com.klasscode.depansmwen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AccountDao extends SQLiteOpenHelper implements DatabaseManager<Account> {

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

    public AccountDao(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
        db.execSQL(create_table_account);
        Log.i("TABLE ACCOUNT","creation success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS account ");
        this.onCreate(db);
    }

    @Override
    public boolean insert(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_USER,account.getIdUser());
        values.put(BANK_NAME,account.getBankName());
        values.put(NUMBER_ACCOUNT,account.getNumberAccount());
        values.put(BALANCE,account.getBalance());
        values.put(IS_ACTIVE,account.isActive());
        values.put(CREATE_AT,account.getCreateAt().toString());
        long f = db.insert(TABLE_ACCOUNT,null, values);
        db.close();
        Log.i("TABLE ACCOUNT","insert success " + f);
        return true;
    }

    @Override
    public boolean update(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(ID_USER,account.getIdUser());
        values.put(BANK_NAME,account.getBankName());
        values.put(NUMBER_ACCOUNT,account.getNumberAccount());
        values.put(BALANCE,account.getBalance());
        values.put(IS_ACTIVE,account.isActive());
        values.put(UPDATE_AT,account.getUpdateAt().toString());
        long f = db.update(TABLE_ACCOUNT, values, KEY_ID + " = ?", new String[]{String.valueOf(account.getId())});
        return true;
    }

    @Override
    public Account get(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ACCOUNT, new String[]{KEY_ID,ID_USER,BANK_NAME,NUMBER_ACCOUNT,BALANCE,
        IS_ACTIVE,CREATE_AT,UPDATE_AT}, KEY_ID + " =? ", new String[]{String.valueOf(id)},null,null,null);
        Account account = new Account();
        if(cursor != null){
           cursor.moveToFirst();

           account.setId(cursor.getInt(0));
           account.setIdUser(cursor.getInt(1));
           account.setBankName(cursor.getString(2));
           account.setNumberAccount(cursor.getLong(3));
           account.setBalance(cursor.getLong(4));
          // account.setActive((boolean)cursor.getString(5));
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
           Date d = null;
           try {
               d = dateFormat.parse(cursor.getString(6));
           } catch (ParseException e) {
               e.printStackTrace();
           }
           account.setCreateAt(d);
       }
        return account;
    }

    @Override
    public List<Account> getAll() {

        return null;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }
}
