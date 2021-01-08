package com.klasscode.depansmwen.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.user.UserDao;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;


public class MainActivity extends AppCompatActivity {

    private EditText txtPseudo;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView createAccout;

    private UserDao dao;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DEBUG","Main Activity as destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEBUG","Main Activity is stop");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new UserDao(this);
        txtPseudo = (EditText) findViewById(R.id.txtPseudo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DEBUG","Event ok");
                String pseudo = txtPseudo.getText().toString();
                String password = txtPassword.getText().toString();

                if(pseudo.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this,R.string.msg_champVide,Toast.LENGTH_SHORT).show();
                }else{

                    User user = dao.checkLogin(pseudo,password);
                    if(user != null){
                        Toast.makeText(MainActivity.this, R.string.msg_connection_reussi,Toast.LENGTH_SHORT).show();
                        Log.i("SUCCESS","User Connected");
                        txtPseudo.setText("");
                        txtPassword.setText("");
                        //Lancer la fenetre principal
                        Intent intent = new Intent(MainActivity.this,HomeAppActivity.class);
                        intent.putExtra("UserConnected",user);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this, R.string.msg_connection_echec,Toast.LENGTH_SHORT).show();
                        txtPseudo.setText("");
                        txtPassword.setText("");
                        Log.i("ERROR","User not Connected");
                    }
                }


            }
        });
        createAccout = (TextView) findViewById(R.id.textCreateAccount);
        createAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}