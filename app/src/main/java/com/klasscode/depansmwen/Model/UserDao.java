package com.klasscode.depansmwen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDao extends SQLiteOpenHelper implements DatabaseManager<User>{


    public UserDao(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user_table= "CREATE TABLE user (" +
                "id integer primary key autoincrement," +
                "username text not null," +
                "pseudo text not null," +
                "email text not null," +
                "password text not null," +
                "create_at date not null" +
                ")";

        db.execSQL(create_user_table);
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
        values.put("create_at",user.getCreateAt().toString());

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
        Cursor cursor = db.query("user",new String[]{"id","username","pseudo","email","password","create_at"},"id = ?",new String[]{String.valueOf(id)},null,null,null);
        while(! cursor.isAfterLast()){
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPseudo(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date d = null;
            try {
                d = dateFormat.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setCreateAt(d);

        }
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
        Cursor cursor = db.query("user",new String[]{"id"},"pseudo = ? and password = ?",new String[]{pseudo,password},null,null,null );
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            user = this.get(cursor.getInt(0));
        }
        return user;
    }

}
