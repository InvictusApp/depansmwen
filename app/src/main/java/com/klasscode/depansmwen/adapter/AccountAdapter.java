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
import com.klasscode.depansmwen.R;

import java.util.ArrayList;

public class AccountAdapter extends BaseAdapter {
    private Activity context;
    ArrayList<Account> accounts;
    AccountDao db;
    BaseAdapter ba;
    private PopupWindow pwindow;
    public AccountAdapter(Activity context, ArrayList<Account> accounts, AccountDao db) {
        this.context = context;
        this.accounts = accounts;
        this.db = db;
    }
    public static class ViewHolder
    {
        TextView textViewBankName;
        TextView textViewNumberAccount;
        Button btnEdit;
    }
    @Override
    public int getCount() {
        return accounts.size();
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
            row = inflater.inflate(R.layout.row_account_item,null,true);
            vh.textViewBankName = (TextView)row.findViewById(R.id.txBankName);
            vh.textViewNumberAccount = (TextView)row.findViewById(R.id.txNumberAccount);
            vh.btnEdit = (Button)row.findViewById(R.id.btnEdit);

            // store the holder with the view.
            row.setTag(vh);
        }else{
            vh = (ViewHolder)view.getTag();
        }

        vh.textViewNumberAccount.setText(accounts.get(i).getBankName());
        vh.textViewNumberAccount.setText((int) accounts.get(i).getNumberAccount());
        final int positionPopup = i;
        vh.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Save: ", "" + positionPopup);
                //editPopup(positionPopup);
            }
        });
        return row;
    }
}
