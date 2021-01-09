package com.klasscode.depansmwen.controller.cash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.Model.cash.CashDaoImpl;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.adapter.CashListAdapter;
import com.klasscode.depansmwen.controller.MainActivity;
import com.klasscode.depansmwen.controller.account.AccountActivity;

import java.util.ArrayList;

public class CashActivity extends AppCompatActivity {

    private Button btnAddCash;
    private ListView listCash;
    private int FIRST_CALL_ID=1234;
    private CashDaoImpl dao;
    private ArrayList<Cash> cashs;
    private CashListAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        btnAddCash = (Button) findViewById(R.id.btnAddCash);
        listCash = (ListView) findViewById(R.id.listCash);
        user = (User)getIntent().getSerializableExtra( MainActivity.USER );
        dao = new CashDaoImpl(this);
        cashs = (ArrayList<Cash>) dao.getAll( MainActivity.USERID );
        adapter = new CashListAdapter(this,cashs,dao);
        for(Cash cash: cashs){
            Log.i("DEBUG","Cash "+cash);
        }

        listCash.setAdapter(adapter);

        btnAddCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CashActivity.this, AddCashActivity.class);
                intent.putExtra(MainActivity.USER, user);
                startActivityForResult(intent,FIRST_CALL_ID);

            }
        });

       /* listCash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CashActivity.this, MainActivity.USERID, Toast.LENGTH_LONG).show();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FIRST_CALL_ID && resultCode == 200){
            Log.i("DEBUG","Callback On activity ok");
            adapter.cashs= (ArrayList<Cash>) dao.getAll( MainActivity.USERID );
            ((BaseAdapter)listCash.getAdapter()).notifyDataSetChanged();
        }
    }

    
}