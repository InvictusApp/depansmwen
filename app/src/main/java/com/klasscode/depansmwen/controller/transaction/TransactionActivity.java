package com.klasscode.depansmwen.controller.transaction;

import androidx.appcompat.app.AppCompatActivity;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.adapter.AccountAdapter;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.account.AccountActivity;
import com.klasscode.depansmwen.controller.account.AddAccountActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactionActivity extends Activity {
    public static final int IDENTITY_TO_TRANSACTION_ACTIVITY = 33;
    private Button mAddBtn;
    private User user;
    private ListView mListView;
    private ArrayList<Account> mAccountArrayList;
    private AccountAdapter mAccountAdapter;
    private AccountDao adao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        adao = new AccountDao(this);
        user = (User) getIntent().getSerializableExtra( MainActivity.USER );
        mAddBtn = (Button) findViewById( R.id.add_transaction_btn );

        mListView = (ListView)findViewById(R.id.listAccount);

        mAddBtn.setText( "Sir/Miss " +user.getUsername()+ " Ajouter une transaction" );
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionActivity.this, AddTransactionActivity.class);
                intent.putExtra(MainActivity.USER, user);
                startActivityForResult(intent, IDENTITY_TO_TRANSACTION_ACTIVITY );
            }
        });
    }
}