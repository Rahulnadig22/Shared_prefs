package com.example.shared_preference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.opengl.ETC1;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    EditText mETusername = findViewById(R.id.user_name);
    EditText mETuseremail = findViewById(R.id.email);
    TextView mLoginmessage = findViewById(R.id.login_message);
    String username = mETusername.getText().toString();
    String email = mETuseremail.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("Login_trials", MODE_PRIVATE);
        editor = prefManager.edit();

        Button mLoginbtn = findViewById(R.id.loginbtn);

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("USERNAME",username);
                editor.putString("EMAIL",email);
                editor.putBoolean("LOGIN_SUCCESS",true);
                editor.apply();
                login();

            }
        });
        boolean isuserloggedin = prefManager.getBoolean("LOGIN_SUCCESS",false);
        if(isuserloggedin){
            login();
        }

    }
    private void login(){
        Intent homescreen = new Intent(LoginActivity.this,HomeActivity.class);
        homescreen.putExtra("USERNAME",username);
        homescreen.putExtra("EMAIL",email);
        startActivity(homescreen);
        finish();
    }
}