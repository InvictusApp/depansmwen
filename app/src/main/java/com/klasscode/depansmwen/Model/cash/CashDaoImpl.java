package com.klasscode.depansmwen.Model.cash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.database.DaoBase;
import com.klasscode.depansmwen.Model.database.DatabaseManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashDaoImpl  extends DaoBase implements DatabaseManager<Cash> {
    public static final String TABLE_NAME = "cash";
    public static final String NAME_USER_TABLE = "user";
    public static final String PRIMARY_KEY_USER = "id";
    public static final String PRIMARY_KEY = "id";
    public static final String FOREIGN_KEY = "idUser";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String CREATE_AT = "createAt";
    public static final String UPDATE_AT = "updateAt";

    public static final String CREATE_CASH_TABLE = "CREATE TABLE " +TABLE_NAME+ " ( "
            +PRIMARY_KEY+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +FOREIGN_KEY+ " INTEGER,"
            +DESCRIPTION+ " TEXT, "
            +AMOUNT+ " REAL, "
            +CREATE_AT+ " DATE NOT NULL, "
            +UPDATE_AT+ " DATE NOT NULL,"
            +" FOREIGN KEY ( " +FOREIGN_KEY+ " ) REFERENCES " +NAME_USER_TABLE+ " ( " +PRIMARY_KEY_USER+ " ));";

    public static final String DROP_CASH_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME+ "; ";

    public CashDaoImpl(@Nullable Context context) {
        super(context);
    }



    @Override
    public boolean insert(Cash cash) {
        //SQLiteDatabase mDb = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        boolean insert = false;
        value.put( FOREIGN_KEY, cash.getIdUser() );
        value.put( DESCRIPTION, cash.getDescription() );
        value.put( AMOUNT, cash.getAmount() );
        value.put( CREATE_AT, cash.getCreateAt().toString() );
        value.put( UPDATE_AT, cash.getUpdateAt().toString() );

        long lineNumberInsert = mDb.insert( TABLE_NAME, null, value );

        if( lineNumberInsert < 0 )
            insert = false;
        else
            insert = true;

        return insert;
    }

    @Override
    public boolean update(Cash cash) {

        //SQLiteDatabase mDb = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        boolean update = false;
        value.put( FOREIGN_KEY, cash.getIdUser() );
        value.put( DESCRIPTION, cash.getDescription() );
        value.put( AMOUNT, cash.getAmount() );
        value.put( CREATE_AT, cash.getCreateAt().toString() );
        value.put( UPDATE_AT, cash.getUpdateAt().toString() );

        int lineNumberUpdate = mDb.update( TABLE_NAME, null, PRIMARY_KEY+ " = ?", new String[]
        { String.valueOf( cash.getId() ) } );

        if( lineNumberUpdate == 0 )
            update = false;
        else
            update = true;

        return update;
    }

    @Override
    public Cash get(int id) {
        Cash cash = null;
        //SQLiteDatabase mDb = this.getReadableDatabase();
        Cursor cursor = mDb.rawQuery( "SELECT * FROM " +TABLE_NAME+ " WHERE " +PRIMARY_KEY+  " = ?",
                new String[] { String.valueOf( id ) } );
        if( cursor.moveToNext() ){
            cash = map( cursor );
        }
        else
            cash = new Cash();

        return cash;
    }

    @Override
    public List<Cash> getAll() {
        List<Cash> cashes = new ArrayList<Cash>();
        //SQLiteDatabase mDb = this.getReadableDatabase();
        Cursor cursor = mDb.rawQuery( "SELECT * FROM " +TABLE_NAME+ ";", new String[]{} );
        while ( cursor.moveToNext() ){
            cashes.add( map( cursor ) );
        }

        return cashes;
    }

    @Override
    public boolean delete(Cash cash) {

        //SQLiteDatabase mDb = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        boolean delete = false;
        int lineNumberDelete = mDb.delete( TABLE_NAME, PRIMARY_KEY + " = ?",
                new String[] { String.valueOf(cash.getId() ) } );

        if( lineNumberDelete == 0 )
            delete = false;
        else
            delete = true;

        return delete;
    }

    private Date parsingDate(Cursor cursor, int colIndex) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse( cursor.getString( colIndex ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    private Cash map ( Cursor cursor ) {
        Cash cash = new Cash();
        cash.setId( cursor.getInt(0 ) );
        cash.setIdUser( cursor.getInt( 1 ) );
        cash.setDescription( cursor.getString( 2 ) );
        cash.setAmount( cursor.getLong( 3 ) );
        cash.setCreateAt( parsingDate( cursor, 4 ) );
        cash.setUpdateAt( parsingDate( cursor, 5 ) );

        return cash;
    }
}
