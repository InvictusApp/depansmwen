package com.klasscode.depansmwen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.UserDao;
import com.klasscode.depansmwen.Model.bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DEBUG", "Signup Activity as destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEBUG", "Signup Activity is stop");
    }

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

                if (username.equals("") || pseudo.equals("") || email.equals("") || password.equals("") || confPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, getString(R.string.msg_champVide), Toast.LENGTH_LONG).show();
                } else if (pseudo.length() < 3) {
                    Toast.makeText(SignUpActivity.this, "Pseudo Trop Court 3 caracteres au moins", Toast.LENGTH_LONG).show();
                } else if (password.length() < 5) {
                    Toast.makeText(SignUpActivity.this, "Password Trop Court 5 caracteres au moins", Toast.LENGTH_LONG).show();
                } else if (password.equals(confPassword)) {

                    boolean checkPseudo = dao.checkPseudo(pseudo);
                    if (!checkPseudo) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String date = dateFormat.format(new Date());

                        Log.i("DEBUG", "date actuelle " + date);

                        user = new User(username, pseudo, email, password, date, null);
                        boolean insert = dao.insert(user);
                        if (insert) {
                            //Popup
                            Toast.makeText(SignUpActivity.this, R.string.msg_succes_creation_compte, Toast.LENGTH_LONG).show();
                            txtUsername.setText("");
                            txtPassword.setText("");
                            txtPseudo.setText("");
                            txtEmail.setText("");
                            txtConfPassword.setText("");
                            SignUpActivity.this.finish();

                        } else {
                            Toast.makeText(SignUpActivity.this, R.string.msg_echec_creation_compte, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, getString(R.string.msg_pseudo_already_use), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, getString(R.string.msg_password_not_same), Toast.LENGTH_SHORT).show();
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