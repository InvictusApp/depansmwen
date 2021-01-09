package com.klasscode.depansmwen.controller.cash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.Model.cash.CashDaoImpl;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.adapter.CashListAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CashActivity extends AppCompatActivity {

    private FloatingActionButton btnAddCash;
    private ListView listCash;
    private int FIRST_CALL_ID=1234;
    private CashDaoImpl dao;
    private ArrayList<Cash> cashs;
    private CashListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        btnAddCash = (FloatingActionButton) findViewById(R.id.btnAddCash);
        listCash = (ListView) findViewById(R.id.listCash);
        User user = (User)getIntent().getSerializableExtra("UserConnected");
        dao = new CashDaoImpl(this);
        cashs = (ArrayList<Cash>) dao.getAll();
        adapter = new CashListAdapter(this,cashs,dao);
        for(Cash cash: cashs){
            Log.i("DEBUG","Cash "+cash);
        }

        listCash.setAdapter(adapter);

        btnAddCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CashActivity.this, AddCashActivity.class);
                intent.putExtra("UserConnected",user);
                startActivityForResult(intent,FIRST_CALL_ID);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FIRST_CALL_ID && resultCode == 200){
            Log.i("DEBUG","Callback On activity ok");
            adapter.cashs= (ArrayList<Cash>) dao.getAll();
            ((BaseAdapter)listCash.getAdapter()).notifyDataSetChanged();
        }
    }

    
}