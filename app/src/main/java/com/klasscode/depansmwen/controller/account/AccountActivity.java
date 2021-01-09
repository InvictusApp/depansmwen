package com.klasscode.depansmwen.controller.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.adapter.AccountAdapter;
import com.klasscode.depansmwen.controller.HomeAppActivity;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.cash.AddCashActivity;
import com.klasscode.depansmwen.controller.cash.CashActivity;

import java.util.ArrayList;

public class AccountActivity extends Activity {
    //private static final String USER = "com.klasscode.depansmwen.controller.HomeAppActivity.USER";
    private Button mAddBtn;
    private User user;
    private ListView mListView;
    ArrayList<Account> mAccountArrayList;
    AccountAdapter mAccountAdapter;
    AccountDao adao;


    public static final int IDENTITY_TO_ACCOUNT_ACTIVITY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
       adao = new AccountDao(this);
        user = (User) getIntent().getSerializableExtra( MainActivity.USER );
        mAddBtn = (Button) findViewById( R.id.add_account_btn );

        mListView = (ListView)findViewById(R.id.listAccount);

        mAddBtn.setText( "Sir/Miss " +user.getUsername()+ " Ajouter un Compte" );
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, AddAccountActivity.class);
                intent.putExtra(MainActivity.USER, user);
                startActivityForResult(intent, IDENTITY_TO_ACCOUNT_ACTIVITY );
            }
        });
        mAccountArrayList = (ArrayList)adao.getAll( user.getId() );
        mAccountAdapter = new AccountAdapter(this,mAccountArrayList,adao);
        mListView.setAdapter(mAccountAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "You Selected " + mAccountArrayList.get(position).getBankName() + " as Bank", Toast.LENGTH_SHORT).show();
            }
        });
                                     
        //Toast.makeText(AccountActivity.this, "Size "+mAccountArrayList.size(), Toast.LENGTH_LONG).show();

       /*for (Account mAccount : mAccountArrayList) {

            String log = "ID "+ mAccount.getId() + "BankName " + mAccount.getBankName() + " number "+mAccount.getNumberAccount() + " Balance " + mAccount.getBalance() + "UserId "+ mAccount.getIdUser();
            // Writing Countries to log
           Toast.makeText(AccountActivity.this, "Size "+log, Toast.LENGTH_LONG).show();
        }*/
    }
}