package com.klasscode.depansmwen.controller.account;

import com.klasscode.depansmwen.Model.account.AccountDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.HomeAppActivity;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.SignUpActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAccountActivity extends Activity{
    private Account account = new Account();
    private User user;
    private AccountDao dao;
    private EditText txtBankName, txtAccountNumber, txtBalance;
    private Button btnAccountAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        dao = new AccountDao( this );

        txtBankName =  ( EditText ) findViewById( R.id.txtBankName );
        txtAccountNumber = ( EditText ) findViewById( R.id.txtNumberAccount );
        txtBalance = ( EditText ) findViewById( R.id.txtBalance );


        btnAccountAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bankName = txtBankName.getText().toString();
                String accountNumber = txtAccountNumber.getText().toString();
                String balance = txtBalance.getText().toString();

                if( bankName.equals( "" ) || accountNumber.equals( "" ) || balance.equals( "" ) ){
                    Toast.makeText(AddAccountActivity.this, getString(R.string.msg_champVide), Toast.LENGTH_LONG).show();

                }else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String date = dateFormat.format(new Date());
                    user = ( User ) getIntent().getSerializableExtra( MainActivity.USER );

                    account.setIdUser( user.getId() );
                    account.setBankName( bankName );
                    account.setNumberAccount( Integer.parseInt( accountNumber ) );
                    account.setBalance( Long.parseLong( balance ) );
                    account.setActive( true );
                    account.setCreateAt( date );
                    account.setUpdateAt( null );
                    boolean accountAdd = dao.insert( account );

                    if( accountAdd ){
                        Toast.makeText(AddAccountActivity.this, R.string.msg_succes_creation_compte, Toast.LENGTH_LONG).show();
                        AddAccountActivity.this.finish();
                    }else{
                        Toast.makeText(AddAccountActivity.this, R.string.msg_echec_creation_compte, Toast.LENGTH_LONG).show();

                    }
                }

            }
        });


    }

}