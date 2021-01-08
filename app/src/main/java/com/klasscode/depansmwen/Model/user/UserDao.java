package com.klasscode.depansmwen.Model.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.Model.database.DaoBase;
import com.klasscode.depansmwen.Model.database.DatabaseManager;

import java.util.List;

public class UserDao extends DaoBase implements DatabaseManager<User> {


    public UserDao(Context pContext) {
        super(pContext);
    }



    @Override
    public boolean insert(User user) {
        ContentValues values = new ContentValues();
        values.put("username",user.getUsername());
        values.put("pseudo",user.getPseudo());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        values.put("create_at",user.getCreateAt());

        long f = mDb.insert("user",null,values);
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
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = mDb.query("user",new String[]{"id","username","pseudo","email","password","create_at,update_at"},"id = ?",new String[]{String.valueOf(id)},null,null,null);
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

        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = mDb.query("user",new String[]{"pseudo"},"pseudo = ?",new String[]{pseudo},null,null,null );
       // Cursor cursor = mDb.rawQuery("select " + INTITULE + " from " + TABLE_NAME + " where salaire > ?", new String[]{"1"});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public User checkLogin(String pseudo,String password) {
        User user = null;
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = mDb.rawQuery("SELECT id,username FROM user WHERE pseudo = ? and password = ?",new String[]{pseudo,password});

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            user = this.get(cursor.getInt(0));
            Log.i("DEBUG","User Connected "+user.getUsername());
            cursor.close();
        }
        return user;
    }

}
