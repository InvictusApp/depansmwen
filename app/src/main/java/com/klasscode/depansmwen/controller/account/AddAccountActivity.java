package com.klasscode.depansmwen.controller.account;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.adapter.AccountAdapter;
import com.klasscode.depansmwen.controller.HomeAppActivity;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.SignUpActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAccountActivity extends Activity{
    private User user;
    private AccountDao dao;
    EditText mEditBankName;
    EditText mEditNumberAcount;
    EditText mEditBalance;
    Button btnAddAccount;

    private Account mAccount;
    private AccountDao adao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        user = (User) getIntent().getSerializableExtra(MainActivity.USER);

        adao = new AccountDao( this );
        //mListView = (ListView)findViewById(R.id.listAccount);

        mEditBankName =  ( EditText ) findViewById( R.id.editBankName );
        mEditNumberAcount = ( EditText ) findViewById( R.id.editNumberAccount );
        mEditBalance = ( EditText ) findViewById( R.id.edit_Balance );
        btnAddAccount = (Button)findViewById(R.id.btnAddAccount);

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("Test","ok");
                String bankName = mEditBankName.getText().toString();
                String accountNumber = mEditNumberAcount.getText().toString();
                String balance = mEditBalance.getText().toString();

                if(!bankName.equals("") && !accountNumber.equals("") && !balance.equals("")){
                    SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
                    String date = dformat.format(new Date());
                        mAccount = new Account(bankName,Long.parseLong( accountNumber ),
                                Double.parseDouble(balance),true );
                        mAccount.setIdUser(MainActivity.USERID);
                    mAccount.setCreateAt(date);
                        //Log.i("Test",""+adao.insert(mAccount));
                        if(adao.insert(mAccount)) {
                            Toast.makeText(AddAccountActivity.this, getString(R.string.msg_AccountInsert), Toast.LENGTH_LONG).show();
                        }
                        //Log.i("Test","BankName " + mAccount.getBankName() + " number "+mAccount.getNumberAccount() + " Balance " + mAccount.getBalance() + "UserId "+ mAccount.getIdUser());
                }else{
                    Toast.makeText(AddAccountActivity.this, getString(R.string.msg_champVide), Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}