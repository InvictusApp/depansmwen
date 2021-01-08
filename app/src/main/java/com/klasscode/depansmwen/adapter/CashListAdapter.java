package com.klasscode.depansmwen.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.cash.CashDaoImpl;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.cash.AddCashActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class CashListAdapter extends BaseAdapter {

    private Activity context;
    public ArrayList<Cash> cashs;
    private CashDaoImpl dao;
    private PopupWindow pwindo;
    BaseAdapter ba;

    public CashListAdapter(Activity context, ArrayList<Cash> cashs, CashDaoImpl dao){
        this.context = context;
        this.cashs = cashs;
        this.dao = dao;
    }

    public static class ViewHolder{
        private TextView lblDescription;
        private TextView lblMontant;
        private Button btnUpdate;
        private Button btnDelete;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View line = convertView;
        ViewHolder vh;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView == null){
            vh = new ViewHolder();
            line = inflater.inflate(R.layout.item_cash,null,true);
            vh.lblDescription = (TextView) line.findViewById(R.id.textDescription);
            vh.lblMontant = (TextView) line.findViewById(R.id.lblMontant);
            vh.btnUpdate =(Button) line.findViewById(R.id.btnUpdate);
            vh.btnDelete =(Button) line.findViewById(R.id.btnDelete);
            line.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.lblDescription.setText(cashs.get(position).getDescription());
        vh.lblMontant.setText(""+cashs.get(position).getAmount());
        final int positionPopup = position;

        vh.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DEBUG","In Event delete in row ");
                dao.delete(cashs.get(positionPopup));
                cashs = (ArrayList) dao.getAll();
                Log.d("Country size", "" + cashs.size());
                notifyDataSetChanged();
            }
        });

        vh.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DEBUG","In Event update in row ");
                showeditPopup(position);
            }
        });
        return line;
    }

    @Override
    public int getCount() {
        return cashs.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void showeditPopup(int positionPopup){
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.cash_edit_popup,
                (ViewGroup) context.findViewById(R.id.popup_layout));
        pwindo = new PopupWindow(layout, 600, 600, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final EditText txtMontant = (EditText) context.findViewById(R.id.textMontant);
        final EditText txtDescription = (EditText) context.findViewById(R.id.textDescription);
        final TextView lblMessage = (TextView) context.findViewById(R.id.lblMessage);

        txtMontant.setText(""+cashs.get(positionPopup).getAmount());
        txtDescription.setText(cashs.get(positionPopup).getDescription());
        int id = cashs.get(positionPopup).getId();
        int idUser = cashs.get(positionPopup).getIdUser();

        Button update = (Button) layout.findViewById(R.id.btnModif);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = txtDescription.getText().toString().trim();
                String montant =txtMontant.getText().toString().trim();
                String d = new String(description);
                if(!d.equals("") && !montant.equals("")) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    try {
                        Number num = df.parse(montant);
                        Double mon = num.doubleValue();
                        if (mon != 0.0d) {
                            Cash cash = new Cash(id,idUser,description,mon,null,new Date());
                            if(dao.update(cash)){

                                cashs = (ArrayList) dao.getAll();
                                lblMessage.setText(R.string.msg_cashInsert);
                                notifyDataSetChanged();
                                pwindo.dismiss();
                            }else{
                                lblMessage.setText(R.string.msg_cashFailed);
                            }
                        }else {
                            lblMessage.setText(R.string.msg_montantIcorect);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}