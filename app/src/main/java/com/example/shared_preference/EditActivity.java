package com.example.shared_preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText muserName = findViewById(R.id.edit_name);
        EditText memail = findViewById(R.id.edit_email);
        Button submit = findViewById(R.id.submit);
        Button cancel = findViewById(R.id.cancel);
        Button newUser = findViewById(R.id.editLogin);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this,HomeActivity.class));
                finish();
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this,LoginActivity.class));
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
            String username = muserName.getText().toString();
            String email = memail.getText().toString();
            Intent homePage = new Intent(EditActivity.this,HomeActivity.class);
            homePage.putExtra("Username",username);
            homePage.putExtra("Email",email);
            startActivity(homePage);
            finish();

        }
        });
    }
}