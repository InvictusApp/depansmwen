package com.klasscode.depansmwen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.UserDao;
import com.klasscode.depansmwen.Model.bean.User;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private TextView lblSignIn;
    private ImageView imageBack;
    private EditText txtUsername;
    private EditText txtPseudo;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfPassword;
    private Button btnInscription;
    private User user;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        dao = new UserDao(this);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPseudo = (EditText) findViewById(R.id.txtPseudo);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfPassword = (EditText) findViewById(R.id.txtConfPassword);
        btnInscription = (Button) findViewById(R.id.btnInscription);
        lblSignIn = (TextView) findViewById(R.id.textSignIn);

        //Listeners
        //listener for sign up
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txtUsername.getText().toString();
                String pseudo = txtPseudo.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confPassword = txtConfPassword.getText().toString();

               if(username.equals("") || pseudo.equals("") || email.equals("") || password.equals("") || confPassword.equals("")){
                   Toast.makeText(SignUpActivity.this,getString(R.string.msg_champVide),Toast.LENGTH_LONG).show();
               }else{
                   if(password.equals(confPassword)){
                       boolean checkPseudo = dao.checkPseudo(pseudo);
                       if(checkPseudo==false){
                            user= new User(username,pseudo,email,password,new Date());
                            boolean insert = dao.insert(user);
                            if(insert == true){
                                //Popup
                                Toast.makeText(SignUpActivity.this, R.string.msg_succes_creation_compte,Toast.LENGTH_LONG).show();
                                txtUsername.setText("");
                                txtPassword.setText("");
                                txtPseudo.setText("");
                                txtEmail.setText("");
                                txtConfPassword.setText("");
                                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(SignUpActivity.this, R.string.msg_echec_creation_compte,Toast.LENGTH_LONG).show();
                            }
                       }else{
                           Toast.makeText(SignUpActivity.this, getString(R.string.msg_pseudo_already_use),Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(SignUpActivity.this, getString(R.string.msg_password_not_same),Toast.LENGTH_SHORT).show();
                   }
               }

            }
        });

        lblSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageBack = (ImageView) findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}