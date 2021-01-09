package com.klasscode.depansmwen.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.account.AccountActivity;
import com.klasscode.depansmwen.controller.cash.CashActivity;

import androidx.appcompat.app.AppCompatActivity;

public class HomeAppActivity extends AppCompatActivity {

    private LinearLayout btnCash;
    private LinearLayout btnCompte;
    private TextView user_connect;
    private Button btnTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        User user = (User) getIntent().getSerializableExtra( MainActivity.USER );
        //User user = (User) getIntent().getSerializableExtra( "UserConnected" );

        btnCash = (LinearLayout) findViewById(R.id.btnCash);
        btnCompte = (LinearLayout) findViewById(R.id.btnCompte);
        user_connect = (TextView) findViewById(R.id.user_connect);
        user_connect.setText(MainActivity.USERNAME + " !");

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

    }
}