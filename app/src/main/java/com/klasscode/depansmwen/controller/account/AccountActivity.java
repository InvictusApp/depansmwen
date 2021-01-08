package com.klasscode.depansmwen.controller.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.HomeAppActivity;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.cash.AddCashActivity;
import com.klasscode.depansmwen.controller.cash.CashActivity;

public class AccountActivity extends Activity {
    //private static final String USER = "com.klasscode.depansmwen.controller.HomeAppActivity.USER";
    private Button mAddBtn;
    private User user;

    public static final int IDENTITY_TO_ACCOUNT_ACTIVITY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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
                startActivityForResult(intent, IDENTITY_TO_ACCOUNT_ACTIVITY );
            }
        });
    }
}