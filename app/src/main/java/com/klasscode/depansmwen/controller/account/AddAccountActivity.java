package com.klasscode.depansmwen.controller.account;

import com.klasscode.depansmwen.Model.bean.User;
import com.klasscode.depansmwen.R;
import com.klasscode.depansmwen.controller.HomeAppActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddAccountActivity extends Activity{
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
    }

}