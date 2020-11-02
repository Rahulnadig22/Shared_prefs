package com.example.shared_preference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    private Bitmap imagepath;
    private ImageView mUserphoto;
    private TextView mUsername;
    private TextView mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mUsername = findViewById(R.id.display_name);
        mEmail = findViewById(R.id.display_email);

        prefManager = getApplicationContext().getSharedPreferences("Login_trials",MODE_PRIVATE);
        editor = prefManager.edit();


        mUserphoto = findViewById(R.id.display_photo);
        Button mbtnEdit = findViewById(R.id.btnedit);
        setScreen(getIntent().getExtras());

        mUserphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivityForResult(camera,909);
            }
        });

        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(HomeActivity.this,EditActivity.class),101);
            }
        });


    }
    private void setScreen(Bundle data){
        if(data != null){
            String userName = data.getString(LoginActivity.USERNAME);
            String userEmail = data.getString(LoginActivity.EMAIL);
            mUsername.setText(userName);
            mEmail.setText(userEmail);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 909){
            if(data.getExtras() != null){
                imagepath = (Bitmap) data.getExtras().get("data");
            }else {
                try {
                    imagepath = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            mUserphoto.setImageBitmap(imagepath);
        }else if(requestCode == 101){
            setScreen(data.getExtras());

            }
        }
    }