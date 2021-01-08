package com.klasscode.depansmwen.Model.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.DatabaseManager;
import com.klasscode.depansmwen.Model.bean.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class UserDao extends SQLiteOpenHelper implements DatabaseManager<User> {

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
    //private static final String KEY_ID = "id";
    private static final String ID_ACCOUNT = "idAccount";
    private static final String TYPE = "type";
    private static final String NUMBER_TRANSFER_ACCOUNT = "numberTransferAccount";
    private static final String AMOUNT = "amount";
  //  private static final String CREATE_AT = "createAt";
//    private static final String UPDATE_AT = "updateAt";


    public UserDao(@Nullable Context context) {
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

        db.execSQL(create_user_table);
        Log.i("DATABASE",db.getPath()+"/"+db.isOpen());
        String create_user_table2= "CREATE TABLE cash (" +
                "id integer primary key autoincrement," +
                "id_user integer not null," +
                "description text not null," +
                "amount double not null," +
                "create_at date ," +
                "update_at date " +
                ")";

        db.execSQL(create_user_table2);

        String create_table_account = "CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_USER + " INTEGER NOT NULL,"
                + BANK_NAME + " TEXT NOT NULL,"
                + NUMBER_ACCOUNT + " LONG NOT NULL,"
                + BALANCE + " double,"
                + IS_ACTIVE + " TEXT,"
                + CREATE_AT + " date,"
                + UPDATE_AT + " date"
                + ")";
        db.execSQL(create_table_account);

        String create_table_transaction = "CREATE TABLE "+ TABLE_TRANSACTION +"("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_ACCOUNT + " INTEGER NOT NULL,"
                + TYPE + " TEXT,"
                + NUMBER_TRANSFER_ACCOUNT + " LONG,"
                + AMOUNT + " double,"
                + CREATE_AT + " date,"
                + UPDATE_AT + " date"
                +")";
        db.execSQL(create_table_transaction);

        Log.i("DATABASE","creation de la table user");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        this.onCreate(db);
    }

    @Override
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

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User get(int id) {

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

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
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
            user = this.get(cursor.getInt(0));
            Log.i("DEBUG","User Connected "+user.getUsername());
            cursor.close();
        }
        return user;
    }

}
