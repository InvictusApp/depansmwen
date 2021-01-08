package com.klasscode.depansmwen.controller.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.klasscode.depansmwen.Model.TestDAO.TestDAO;
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
import java.util.List;

public class AccountActivity extends Activity {
    //private static final String USER = "com.klasscode.depansmwen.controller.HomeAppActivity.USER";
    private Button mAddBtn;
    private User user;
    ArrayList<Account> mAccountArrayList;
    //AccountDao db;
    TestDAO db;
    Activity activity;
    ListView listView;
    AccountAdapter mAccountAdapter;

    public static final int IDENTITY_TO_ACCOUNT_ACTIVITY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        activity = this;
        //db = new AccountDao(this);
        db = new TestDAO(this);
        //listView = (ListView) findViewById(R.id.listAccount);*/

        user = (User) getIntent().getSerializableExtra( MainActivity.USER );
        mAddBtn = (Button) findViewById( R.id.add_account_btn );

        mAddBtn.setText( "Sir/Miss " +user.getUsername()+ " Ajouter un Compte" );
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(AccountActivity.this, AddAccountActivity.class);
                intent.putExtra( MainActivity.USER, user);
                startActivity(intent);*/
                Intent intent = new Intent(AccountActivity.this, AddAccountActivity.class);
                //startActivityForResult(intent, IDENTITY_TO_ACCOUNT_ACTIVITY );
                startActivity(intent);

            }
        });
       /* Account ac = new Account("unibank",500,500,true);
        ac.setIdUser(1);
        Log.i("Test",""+db.insert(ac));*/
        mAccountArrayList = (ArrayList) db.getAll();

        for (Account account : mAccountArrayList) {
            String log = "Id: " + account.getId() + " ,Name: " + account.getBankName() + " ,Number Account: " + account.getNumberAccount();
            // Writing Countries to log
            Log.i("Name: ", log);
        }
    }
}