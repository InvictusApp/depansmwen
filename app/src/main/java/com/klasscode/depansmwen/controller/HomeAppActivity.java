package com.klasscode.depansmwen.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.klasscode.depansmwen.Model.bean.User;

import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.account.AccountActivity;
import com.klasscode.depansmwen.controller.cash.CashActivity;
import com.klasscode.depansmwen.controller.transaction.TransactionActivity;

public class HomeAppActivity extends AppCompatActivity {

    private Button btnCash;
    private Button btnCompte;
    private Button btnTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        User user = (User) getIntent().getSerializableExtra( MainActivity.USER );
        //User user = (User) getIntent().getSerializableExtra( "UserConnected" );

        btnCash = findViewById(R.id.btnCash);
        btnCompte = findViewById(R.id.btnCompte);
        //btnTransaction = findViewById( R.id.btnTransaction );

        //btnCompte.setText( btnCompte.getText().toString()+ "cmp" +user.getUsername() );
        btnCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeAppActivity.this, AccountActivity.class);
                intent.putExtra( MainActivity.USER, user);
                //intent.putExtra( "UserConnected", user);
                startActivity(intent);
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeAppActivity.this, CashActivity.class);
                intent.putExtra( MainActivity.USER, user);
                //intent.putExtra( "UserConnected", user);
                startActivity(intent);
            }
        });

        /*btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeAppActivity.this, TransactionActivity.class);
                intent.putExtra( MainActivity.USER, user);
                //intent.putExtra( "UserConnected", user);
                startActivity(intent);
            }
        });*/
    }
}