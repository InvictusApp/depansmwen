package com.klasscode.depansmwen.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.Transaction;
import com.klasscode.depansmwen.Model.transaction.TransactionDao;
import com.klasscode.depansmwen.R;

import java.util.ArrayList;

public class TransactionAdapter extends BaseAdapter {
    private Activity context;
    ArrayList<Transaction> mTransactionArrayList;
    TransactionDao db;
    BaseAdapter ba;
    private PopupWindow pwindowAccount;

    public TransactionAdapter(Activity context, ArrayList<Transaction> accounts, TransactionDao db) {
        this.context = context;
        this.mTransactionArrayList = accounts;
        this.db = db;
    }

    public static class ViewHolder
    {
        TextView textViewTransactionType;
        TextView textViewAmount;
        Button btnEdit;
        Button btnDelete;
    }
    @Override
    public int getCount() {
        return mTransactionArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if(view == null){
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.row_transaction_item,null,true);
            vh.textViewTransactionType = (TextView)row.findViewById(R.id.txTransactionType);
            vh.textViewAmount = (TextView)row.findViewById(R.id.txViewAmount);
            vh.btnEdit = (Button)row.findViewById(R.id.btnEdit);
            vh.btnDelete = (Button)row.findViewById(R.id.btnDelete);
            row.setTag(vh);
        }else{
            vh = (TransactionAdapter.ViewHolder)view.getTag();
        }
        vh.textViewAmount.setText((int) mTransactionArrayList.get(i).getAmount());
        vh.textViewTransactionType.setText(mTransactionArrayList.get(i).getType());
        final int positionPopup = i;
        vh.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Save: ", "" + positionPopup);
                //editPopup(positionPopup);
            }
        });
        vh.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(mTransactionArrayList.get(i));
                mTransactionArrayList = (ArrayList)db.getAll( i );
                Log.i("test delete element ", ""+mTransactionArrayList.size());
                notifyDataSetChanged();
            }
        });
        return row;
    }
}
