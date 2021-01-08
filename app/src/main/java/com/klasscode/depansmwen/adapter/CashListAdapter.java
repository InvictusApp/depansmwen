package com.klasscode.depansmwen.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.klasscode.depansmwen.Model.bean.Cash;
import com.klasscode.depansmwen.Model.cash.CashDaoImpl;
import com.klasscode.depansmwen.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CashListAdapter extends BaseAdapter {

    private Activity context;
    public ArrayList<Cash> cashs;
    private CashDaoImpl dao;
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
            vh.lblDescription = (TextView) line.findViewById(R.id.lblDescription);
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
}
