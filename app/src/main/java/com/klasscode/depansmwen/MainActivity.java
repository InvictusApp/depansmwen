package com.klasscode.depansmwen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.AccountDao;
import com.klasscode.depansmwen.Model.UserDao;
import com.klasscode.depansmwen.Model.bean.Account;
import com.klasscode.depansmwen.Model.bean.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText txtPseudo;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView createAccout;

    private UserDao dao;
    //TEST ACCOUNT
    private AccountDao adao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEST
        adao = new AccountDao(this);
        Account a ;
        /*Account a = new Account("UNIBANK",12345,500,true);
        a.setIdUser(1);
        a.setCreateAt(new Date());

        boolean insert = adao.insert(a);
        Log.i("TEST",""+insert);*/
        //GET
        /*a = adao.get(1);
        Log.i("TESTGET","NAME "+a.getBankName()+" "+a.getNumberAccount()+" "+a.getBalance()+ " "+a.createAt());*/

        //update
       /* a = new Account("SogeBANK",12345,500,true);
        a.setIdUser(1);
        a.setId(1);
        a.setUpdateAt(new Date());
        adao.update(a);
        Account a1 = adao.get(1);
        Log.i("TESTGET","NAME Apres "+a1.getBankName());*/

        //List
       /* List<Account> list ;
        list = adao.getAll();
        for(int i =0; i < list.size(); i++) {
            Account a2 = list.get(i);
            Log.i("TESTAll", a2.getId()+"name " + a2.getBankName());
        }*/

        //delete
        /*a = new Account();
        a.setId(3);
        adao.delete(a);
        List<Account> list2 ;
        list2 = adao.getAll();
        for(int i =0; i < list2.size(); i++) {
            Account a2 = list2.get(i);
            Log.i("TESTAll", a2.getId()+"name " + a2.getBankName());
        }*/
        txtPseudo = (EditText) findViewById(R.id.txtPseudo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        dao = new UserDao(this);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        intent.putExtra("userConnected",user);
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