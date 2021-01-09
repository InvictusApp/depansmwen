package com.klasscode.depansmwen.Model.transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.DatabaseManager;
import com.klasscode.depansmwen.Model.bean.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDao extends SQLiteOpenHelper implements DatabaseManager<Transaction> {
    //Transaction table name
    private static final String TABLE_TRANSACTION = "Transac";

    //Transaction table Columns names
    private static final String KEY_ID = "id";
    private static final String ID_ACCOUNT = "idAccount";
    private static final String TYPE = "type";
    private static final String NUMBER_TRANSFER_ACCOUNT = "numberTransferAccount";
    private static final String AMOUNT = "amount";
    private static final String CREATE_AT = "createAt";
    private static final String UPDATE_AT = "updateAt";

    public TransactionDao(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String create_table_transaction = "CREATE TABLE "+ TABLE_TRANSACTION +"("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_ACCOUNT + " INTEGER NOT NULL,"
                + TYPE + " TEXT,"
                + NUMBER_TRANSFER_ACCOUNT + " LONG,"
                + AMOUNT + " LONG,"
                + CREATE_AT + " date,"
                + UPDATE_AT + " date"
                +")";
        db.execSQL(create_table_transaction);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Transac ");
        this.onCreate(db);
    }

    @Override
    public boolean insert(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(KEY_ID, transaction.getId());
        values.put(ID_ACCOUNT,transaction.getIdAccount());
        values.put(TYPE,transaction.getType());
        values.put(NUMBER_TRANSFER_ACCOUNT,transaction.getNumberTransferAccount());
        values.put(AMOUNT,transaction.getAmount());
        values.put(CREATE_AT,transaction.getCreateAt().toString());
        long f = db.insert(TABLE_TRANSACTION,null, values);
        return (f > 0) ? true : false;
    }

    @Override
    public boolean update(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(KEY_ID, transaction.getId());
        //values.put(ID_ACCOUNT,transaction.getIdAccount());
        values.put(TYPE,transaction.getType());
        values.put(NUMBER_TRANSFER_ACCOUNT,transaction.getNumberTransferAccount());
        values.put(AMOUNT,transaction.getAmount());
        values.put(UPDATE_AT,transaction.getUpdateAt().toString());
        long f = db.update(TABLE_TRANSACTION, values, KEY_ID + " = ?", new String[]{String.valueOf(transaction.getId())});
        return (f != 0) ? true : false;
    }

    @Override
    public Transaction get(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRANSACTION,new String[]{KEY_ID,ID_ACCOUNT,TYPE,NUMBER_TRANSFER_ACCOUNT,AMOUNT,CREATE_AT,UPDATE_AT},
                ID_ACCOUNT + " = ?", new String[]{String.valueOf(id)},null,null,null);

        Transaction tr = null;
        if(cursor != null){
            tr = new Transaction();
            cursor.moveToFirst();
            tr.setId(cursor.getInt(0));
            tr.setIdAccount(cursor.getInt(1));
            tr.setType(cursor.getString(2));
            tr.setNumberTransferAccount(cursor.getLong(3));
            tr.setAmount(cursor.getLong(4));
            tr.setCreateAt(cursor.getString(5));

            /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date d = null;
            try {
                d = dateFormat.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tr.setCreateAt(d);*/

        }
        return tr;
    }

    @Override
    public List<Transaction> getAll( int id ) {
        List<Transaction> transactionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE idAccount = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            do{
                Transaction tr = new Transaction();
                tr.setId(cursor.getInt(0));
                tr.setIdAccount(cursor.getInt(1));
                tr.setType(cursor.getString(2));
                tr.setNumberTransferAccount(cursor.getLong(3));
                tr.setAmount(cursor.getLong(4));
                tr.setCreateAt(cursor.getString(5));

                /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date d = null;
                try {
                    d = dateFormat.parse(cursor.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tr.setCreateAt(d);*/

                transactionList.add(tr);
            }while (cursor.moveToNext());
        }
        return transactionList;
    }

    @Override
    public boolean delete(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        long f = db.delete(TABLE_TRANSACTION,KEY_ID + " = ?", new String[]{String.valueOf(transaction.getId())});
        db.close();
        return (f > 0) ? true : false;
    }
}
