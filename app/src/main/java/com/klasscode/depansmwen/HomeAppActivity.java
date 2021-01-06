package com.klasscode.depansmwen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.klasscode.depansmwen.Model.bean.User;

public class HomeAppActivity extends AppCompatActivity {

    private Button btnCash;
    private Button btnCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        User user = (User) getIntent().getSerializableExtra("UserConnected");

        btnCash = findViewById(R.id.btnCash);
        btnCompte = findViewById(R.id.btnCompte);

        btnCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeAppActivity.this,AccountActivity.class);
                intent.putExtra("UserConnected",user);
                startActivity(intent);
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeAppActivity.this,CashActivity.class);
                intent.putExtra("UserConnected",user);
                startActivity(intent);
            }
        });
    }
}