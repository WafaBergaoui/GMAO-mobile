package com.example.gmaomobile.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gmaomobile.Model.Database;
import com.example.gmaomobile.R;
import com.example.gmaomobile.utils.Session;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login, register;
    private EditText etEmail, etPass;
    private Database db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new Database(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:

        }
    }
    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(), "Entrez votre Email/Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(db.getUser(email,pass)){
                session.setLoggedin(true);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
