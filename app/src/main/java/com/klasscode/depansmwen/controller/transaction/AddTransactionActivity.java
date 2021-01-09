package com.klasscode.depansmwen.controller.transaction;

import androidx.appcompat.app.AppCompatActivity;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.Transaction;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.Model.transaction.TransactionDao;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.account.AddAccountActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransactionActivity extends AppCompatActivity {
    private User user;
    private AccountDao dao;
    private EditText mEditBankName, mEditNumberAcount, mEditBalance;
    private Button btnAddTransaction;

    private Transaction transaction;
    private TransactionDao adao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        user = (User) getIntent().getSerializableExtra(MainActivity.USER);

        adao = new TransactionDao( this );
        //mListView = (ListView)findViewById(R.id.listAccount);

        mEditBankName =  ( EditText ) findViewById( R.id.editBankName );
        mEditNumberAcount = ( EditText ) findViewById( R.id.editNumberAccount );
        mEditBalance = ( EditText ) findViewById( R.id.edit_Balance );
        btnAddTransaction = (Button)findViewById( R.id.btnAddTransaction );

        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("Test","ok");
                String bankName = mEditBankName.getText().toString();
                String accountNumber = mEditNumberAcount.getText().toString();
                String balance = mEditBalance.getText().toString();

                /*if(!bankName.equals("") && !accountNumber.equals("") && !balance.equals("")){
                    SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
                    String date = dformat.format(new Date());
                    mAccount = new Account(user.getId(),bankName,Long.parseLong( accountNumber ),
                            Double.parseDouble(balance),true, date, date );
                    //mAccount.setIdUser(user.getId());
                    //mAccount.setIdUser(1);
                    Log.i("Test",""+adao.insert(mAccount));
                    if(adao.insert(mAccount)) {
                        Toast.makeText(AddAccountActivity.this, getString(R.string.msg_AccountInsert), Toast.LENGTH_LONG).show();
                    }
                    //Log.i("Test","BankName " + mAccount.getBankName() + " number "+mAccount.getNumberAccount() + " Balance " + mAccount.getBalance() + "UserId "+ mAccount.getIdUser());
                }else{
                    Toast.makeText(AddAccountActivity.this, getString(R.string.msg_champVide), Toast.LENGTH_LONG).show();

                }*/

            }
        });
    }
}