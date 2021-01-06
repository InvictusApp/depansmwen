package com.klasscode.depansmwen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.klasscode.depansmwen.Model.bean.Transaction;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS account ");
        this.onCreate(db);
    }

    @Override
    public boolean insert(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, transaction.getId());
        values.put(ID_ACCOUNT,transaction.getIdAccount());
        values.put(TYPE,transaction.getType());
        values.put(NUMBER_TRANSFER_ACCOUNT,transaction.getNumberTransferAccount());
        values.put(AMOUNT,transaction.getAmount());
        values.put(CREATE_AT,transaction.getCreateAt().toString());
        return true;
    }

    @Override
    public boolean update(Transaction transaction) {
        return false;
    }

    @Override
    public Transaction get(int id) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public boolean delete(Transaction transaction) {
        return false;
    }
}
