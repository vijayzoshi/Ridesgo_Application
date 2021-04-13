package com.example.xml;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PhoneLoginActivity extends AppCompatActivity {


    private Button mButton;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);


        mButton = findViewById(R.id.track);
        mEditText= findViewById(R.id.editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditText.getText().toString())){
                    Toast.makeText(PhoneLoginActivity.this, "Type Mobile Number", Toast.LENGTH_LONG).show();
                }else if(mEditText.getText().toString().length()!=10){
                    Toast.makeText(PhoneLoginActivity.this, "Type Correct Mobile Number", Toast.LENGTH_LONG).show(); }
                else{




                    Intent intent = new Intent(PhoneLoginActivity.this, VerificationActivity.class);
                    intent.putExtra("number", "+91" + mEditText.getText().toString());

                    startActivity(intent);
                }
            }

        });

























    }}

