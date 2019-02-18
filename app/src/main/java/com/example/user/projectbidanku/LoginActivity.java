package com.example.user.projectbidanku;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.DrawerActivity.MenuActivity;
import com.example.user.projectbidanku.Model.VolleyCalback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoCompleteTextView etEmail;
    private TextInputEditText etPassword;
    private AppCompatButton button;
    private ServerHelper serverHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(LoginActivity.this);
        if(sessionManager.isLogedIn()){
            startActivity(new Intent(LoginActivity.this,MenuActivity.class));
            finish();
        }
        etEmail = (AutoCompleteTextView) findViewById(R.id.etEmaail);
        etPassword = (TextInputEditText) findViewById(R.id.etPass);
        button = (AppCompatButton) findViewById(R.id.btnLogin);

        serverHelper = new ServerHelper(LoginActivity.this);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        if(etEmail.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")){
//            sessionManager.setLogin("admin",1);
//            Toast.makeText(LoginActivity.this,"Login Berhasil",Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(LoginActivity.this,MenuActivity.class));
//            finish();
//        }
        serverHelper.loginFunction(etEmail.getText().toString(), etPassword.getText().toString(), new VolleyCalback() {

            @Override
            public void onSuccess(String result, String result2) {
                if(!result.equals("")){
                    sessionManager.setLogin(result,Integer.parseInt(result2));
                    Toast.makeText(LoginActivity.this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
