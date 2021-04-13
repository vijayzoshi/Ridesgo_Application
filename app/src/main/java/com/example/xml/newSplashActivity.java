package com.example.xml;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class newSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   new Handler().postDelayed(new Runnable() {
        //     @Override
        //   public void run() {
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            Intent intent = new Intent(newSplashActivity.this, PhoneLoginActivity.class);


            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(newSplashActivity.this, MainActivity.class);


            startActivity(intent);
            finish();
        }
        // }
        //},3000);

    }
}


