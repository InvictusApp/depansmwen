package com.klasscode.depansmwen.controller.cash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;

public class CashActivity extends AppCompatActivity {

    private Button btnAddCash;
    private PopupWindow pwindo;
    private ListView listCash;
    private int FIRST_CALL_ID=1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        btnAddCash = (Button) findViewById(R.id.btnAddCash);
        listCash = (ListView) findViewById(R.id.listCash);
        User user = (User)getIntent().getSerializableExtra("UserConnected");

        btnAddCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CashActivity.this, AddCashActivity.class);
                intent.putExtra("UserConnected",user);
                startActivityForResult(intent,FIRST_CALL_ID);
            }
        });

    }

}