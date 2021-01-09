package com.klasscode.depansmwen.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.bean.Transaction;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.HomeAppActivity;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.account.AccountActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountAdapter extends BaseAdapter {
    private Activity context;
    public ArrayList<Account> accounts;
    AccountDao db;
    BaseAdapter ba;
    private PopupWindow pwindowAccount;
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
        Button btnDelete;
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
            vh.btnDelete = (Button)row.findViewById(R.id.btnDelete);

            // store the holder with the view.
            row.setTag(vh);
        }else{
            vh = (ViewHolder)view.getTag();
        }

        vh.textViewBankName.setText(accounts.get(i).getBankName());
        vh.textViewNumberAccount.setText(""+ accounts.get(i).getNumberAccount());
        final int positionPopup = i;
        vh.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Save: ", "" + positionPopup);
                //Toast.makeText(AccountAdapter.this, "Size "+positionPopup+"userId"+MainActivity.USERID, Toast.LENGTH_LONG).show();
                editPopup(positionPopup);
            }
        });
        vh.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(accounts.get(i));
                accounts = (ArrayList)db.getAll(MainActivity.USERID);
                Log.i("test delete element ", ""+accounts.size());
                notifyDataSetChanged();
            }
        });
        return row;
    }

    private void editPopup(int positionPopup) {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.account_edit_popup,
                (ViewGroup) context.findViewById(R.id.account_popup_edit));
        pwindowAccount = new PopupWindow(layout, 600, 600, true);
        pwindowAccount.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final EditText txtBankN = (EditText) layout.findViewById(R.id.editBankNameM);
        final EditText txtNumberAccount = (EditText) layout.findViewById(R.id.editNumberAccountM);
        final EditText txBalance = (EditText) layout.findViewById(R.id.edit_BalanceM);
        //Integrate klasscode
        final RadioButton radioActive = (RadioButton) layout.findViewById(R.id.active);
        final RadioButton radioInactive = (RadioButton) layout.findViewById(R.id.inactive);
        boolean accountActived = false;

        txtBankN.setText(""+accounts.get(positionPopup).getBankName());
        txtNumberAccount.setText(""+accounts.get(positionPopup).getNumberAccount());
        txBalance.setText(""+accounts.get(positionPopup).getBalance());
        //Integrate klasscode
        accountActived = accounts.get(positionPopup).isActive();

        if(accountActived){
            radioActive.setChecked(true);
        }
        if(!accountActived){
            radioInactive.setChecked(true);
        }
        int id = accounts.get(positionPopup).getId();
        int idUser = accounts.get(positionPopup).getIdUser();
        //String creat_at = cashs.get(positionPopup).getCreateAt();

       Button update = (Button) layout.findViewById(R.id.btnUpdateAccount);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String bankN =  txtBankN.getText().toString().trim();
                 String numberAc = txtNumberAccount.getText().toString().trim();
                 String balance = txBalance.getText().toString().trim();

                if(!bankN.equals("") && !numberAc.equals("") && !balance.equals("")) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    try {
                        Number num = df.parse(balance);
                        Double mon = num.doubleValue();
                        if (mon != 0.0d) {
                            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                            String date =f.format(new Date());
                            Account account = new Account(bankN,Long.parseLong(numberAc),mon,true);
                            account.setUpdateAt(date);
                            account.setIdUser(idUser);
                            account.setId(id);
                           // Log.i("DATABASE","Cash : "+cash);
                            if(db.update(account)){
                                Log.i("DATABASE","In update");
                                accounts = (ArrayList) db.getAll( idUser );
                                //lblMessage.setText(R.string.msg_cashInsert);
                                notifyDataSetChanged();
                                pwindowAccount.dismiss();
                            }else{
                                //lblMessage.setText(R.string.msg_cashFailed);
                            }
                        }else {
                            //lblMessage.setText(R.string.msg_montantIcorect);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        

        Button cancel = (Button) layout.findViewById(R.id.btnCancelAccount);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwindowAccount.dismiss();
            }
        });

    }
}
