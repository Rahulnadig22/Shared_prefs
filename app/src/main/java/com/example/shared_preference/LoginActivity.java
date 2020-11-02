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
    public static String USERNAME="USERNAME";
    public static String EMAIL = "EMAIL";
    EditText mETusername;
    EditText mETuseremail;
    TextView mLoginmessage;
    String username;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mETusername = findViewById(R.id.user_name);
        mETuseremail = findViewById(R.id.email);
        mLoginmessage = findViewById(R.id.login_message);
        prefManager = getApplicationContext().getSharedPreferences("Login_trials", MODE_PRIVATE);
        editor = prefManager.edit();

        Button mLoginbtn = findViewById(R.id.loginbtn);
        username = mETusername.getText().toString();
        email = mETuseremail.getText().toString();

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(USERNAME,username);
                editor.putString(EMAIL,email);
                editor.putBoolean("LOGIN_SUCCESS",true);
                editor.apply();
                login();

            }
        });
        boolean isuserloggedin = prefManager.getBoolean("LOGIN_SUCCESS",false);
        username = prefManager.getString(USERNAME,"");
        email = prefManager.getString(EMAIL,"");
        if(isuserloggedin){
            login();
        }

    }
    private void login(){
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        finish();
    }
}