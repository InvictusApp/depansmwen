package com.klasscode.depansmwen.controller.cash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.Model.cash.CashDaoImpl;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.MainActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCashActivity extends AppCompatActivity {

    private EditText txtDescription;
    private EditText txtMontant;
    private Button btnSave;
    private CashDaoImpl dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);

        dao= new CashDaoImpl(this);
        dao.getWritableDatabase();

        txtDescription = (EditText)findViewById(R.id.txtDescription);
        txtMontant = (EditText) findViewById(R.id.txtAmount);
        btnSave = (Button) findViewById(R.id.btnSave);
        User user = (User) getIntent().getSerializableExtra(MainActivity.USER);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = txtDescription.getText().toString();
                //Log.i("DEBUG","Description "+ txtDescription.getText().toString());
                Log.i("DEBUG","Description "+ description);
                String amount = txtMontant.getText().toString();
                String d = new String(description);
                if(!d.equals("") && !amount.equals("")){
                    DecimalFormat df = new DecimalFormat("0.00");
                    try {
                        Number num = df.parse(amount);
                        Double mon = num.doubleValue();
                        if(mon!=0.0d){
                            Cash cash = new Cash();
                            cash.setIdUser( user.getId() );
                            cash.setDescription(d);
                            cash.setAmount(mon);
                            SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
                            String date = dformat.format(new Date());
                            cash.setCreateAt(date);
                            cash.setUpdateAt(date);
                            if(dao.insert(cash)){
                                Toast.makeText(AddCashActivity.this, getString(R.string.msg_cashInsert),Toast.LENGTH_LONG).show();
                                //Inform the list view the change of cash
                                setResult(200);
                                AddCashActivity.this.finish();

                            }else{
                                Toast.makeText(AddCashActivity.this, getString(R.string.msg_cashFailed),Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(AddCashActivity.this, getString(R.string.msg_montantIcorect),Toast.LENGTH_LONG).show();
                        }
                    } catch (ParseException e) {
                        Toast.makeText(AddCashActivity.this, getString(R.string.msg_montantIcorect),Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(AddCashActivity.this,getString(R.string.msg_champVide),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}