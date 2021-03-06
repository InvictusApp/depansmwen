package com.klasscode.depansmwen.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.Transaction;
import com.klasscode.depansmwen.Model.user.UserDao;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText txtPseudo;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView createAccout;
    public static int USERID = 0;
    public static String USERNAME = "";
    public static final String USER = "com.klasscode.depansmwen.controller.USER";
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
        //initi
        //SharePreferences in order to keep a user info
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

       /* SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", null);
        editor.putInt("userId",0);
        editor.apply();*/

        //Check if user already log in
        USERNAME = getPreferences(MODE_PRIVATE).getString("username", null);
        USERID =  getPreferences(MODE_PRIVATE).getInt("userId", 0);

        if(USERNAME != null){
            Intent intent = new Intent(MainActivity.this,HomeAppActivity.class);
            startActivity(intent);
        }

        dao = new UserDao(this);

        //TEST
       /*AccountDao adao = new AccountDao(this);
       Account a1 = new Account("unibank",123456,500,true);
       a1.setIdUser(1);
       a1.setCreateAt(new Date());

        Log.i("test insert ","" + adao.insert(a1));
        Account a = adao.get(1);
        Log.i("test get ","Name " + a.getBankName()+"date "+a.getCreateAt());*/

        //TEST TRANSACTION
        //TransactionDao tdao = new TransactionDao(this);
        //TransactionDao tdao = new TransactionDao(this);

        /*Transaction tr = new Transaction(1,"depot",0,700);
        tr.setCreateAt(new Date());
        Log.i("test insert ","" +tdao.insert(tr));

        Transaction tr1 = new Transaction(1,"transfer",7867868,1700);
        tr1.setCreateAt(new Date());
        Log.i("test insert","" +tdao.insert(tr1));
        //get
        Transaction t1 = tdao.get(1);
        Log.i("test get","" +t1.getAmount());

        Transaction tr2 = new Transaction(1,"transfer",7867868,2700);
        tr2.setUpdateAt(new Date());
        tr2.setId(1);
        Log.i("test update", ""+tdao.update(tr2));

        Transaction t12 = tdao.get(1);
        Log.i("test get","" +t12.getAmount());*/
        //getlist
       /* List<Transaction> listt = tdao.getAll();
        for(int i =0; i< listt.size(); i++){
            Transaction t = listt.get(i);
            Log.i("test list",""+t.getId()+"" +t.getAmount()+""+t.getType()+""+t.getCreateAt());
        }
        //delete
        Transaction t = new Transaction();
        t.setId(1);
        Log.i("test delete",""+tdao.delete(t));
        //getlist
        List<Transaction> list = tdao.getAll();
        for(int i =0; i< list.size(); i++){
            Transaction tt = list.get(i);
            Log.i("test list",""+tt.getId()+"" +tt.getAmount()+""+tt.getType()+""+tt.getCreateAt());
        }*/

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


                        //Store a user info
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", user.getUsername());
                        editor.putInt("userId",user.getId());
                        editor.apply();
                        USERNAME = user.getUsername();
                        USERID = user.getId();
                        //Toast.makeText(MainActivity.this, "userId "+user.getId() + "Username "+ user.getUsername(),Toast.LENGTH_SHORT).show();

                        //Lancer la fenetre principal
                        Intent intent = new Intent(MainActivity.this,HomeAppActivity.class);
                        //intent.putExtra("UserConnected", user);
                        intent.putExtra( USER, user);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this, R.string.msg_connection_echec,Toast.LENGTH_SHORT).show();
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