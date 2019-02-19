package com.deliveryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mail;
    private EditText password;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        login = findViewById(R.id.login_btn);
        configureEditTexts();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMandatoryEditTexts()){
                    //TODO: Login User
                    Toast.makeText(LoginActivity.this, "Login okay", Toast.LENGTH_SHORT).show();
                }
                else{
                    //TODO: Show Error
                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configureEditTexts(){
        mail.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        password.setImeOptions(EditorInfo.IME_ACTION_DONE);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    login.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean checkMandatoryEditTexts(){
        if(mail.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}
