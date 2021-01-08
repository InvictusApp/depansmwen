package com.klasscode.depansmwen.Model.TestDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDAO extends SQLiteOpenHelper {
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


    public TestDAO(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

        db.execSQL(create_user_table);
        db.execSQL(create_table_account);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + "user");
    }


    public boolean insert(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",user.getUsername());
        values.put("pseudo",user.getPseudo());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        values.put("create_at",user.getCreateAt());

        long f = db.insert("user",null,values);
        Log.i("DATABASE","Insertion reussie "+ f);
        return true;
    }


    public User getU(int id) {

        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",new String[]{"id","username","pseudo","email","password","create_at,update_at"},"id = ?",new String[]{String.valueOf(id)},null,null,null);
        while(! cursor.isAfterLast()){
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPseudo(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setCreateAt(cursor.getString(5));
            user.setUpdateAt(cursor.getString(6));
            cursor.moveToNext();
        }
        cursor.close();
        return user;
    }

    public List<Account> getAll() {
        List<Account> accountList = new ArrayList<Account>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNT;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Account account = new Account();
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
                accountList.add(account);
            }while(cursor.moveToNext());
        }
        return accountList;
    }


    public boolean insertA(Account account) {
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
        // Log.i("TABLE ACCOUNT","insert success " + f);
        return (f > 0) ? true : false;
    }

    public boolean checkPseudo(String pseudo) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",new String[]{"pseudo"},"pseudo = ?",new String[]{pseudo},null,null,null );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public User checkLogin(String pseudo,String password) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,username FROM user WHERE pseudo = ? and password = ?",new String[]{pseudo,password});

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            user = this.getU(cursor.getInt(0));
            Log.i("DEBUG","User Connected "+user.getUsername());
            cursor.close();
        }
        return user;
    }



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
}
