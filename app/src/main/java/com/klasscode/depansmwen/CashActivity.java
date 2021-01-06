package com.klasscode.depansmwen;

import androidx.appcompat.app.AppCompatActivity;

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

public class CashActivity extends AppCompatActivity {

    private Button btnAddCash;
    private PopupWindow pwindo;
    private ListView listCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        btnAddCash = (Button) findViewById(R.id.btnAddCash);
        listCash = (ListView) findViewById(R.id.listCash);

        btnAddCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showpopup();
            }
        });

    }

    private void showpopup() {
        LayoutInflater inflater = this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.popup_add_cash,
                (ViewGroup) this.findViewById(R.id.popup_layout));
        pwindo = new PopupWindow(layout, 600, 670, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        EditText txtDescription = (EditText) findViewById(R.id.txtDescription);
        EditText txtAmount = (EditText) findViewById(R.id.txtMontant);
        Button addButton = (Button) findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = txtDescription.getText().toString();
                Double amount = Double.parseDouble(txtAmount.getText().toString());
                if(description.equals("") || amount== 0.0){
                    Toast.makeText(CashActivity.this,getString(R.string.msg_champVide),Toast.LENGTH_LONG).show();
                }else if(amount <= 0.0){
                    Toast.makeText(CashActivity.this,"Montant Incorrect",Toast.LENGTH_LONG).show();
                }else{

                    //Insertion in CashDao
                }
            }
        });

    }
}