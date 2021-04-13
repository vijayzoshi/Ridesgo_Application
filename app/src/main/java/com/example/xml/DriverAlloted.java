package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverAlloted extends AppCompatActivity {


    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_alloted);


//        History fragment = new History();
  //      FragmentManager manager = getSupportFragmentManager();

    //    manager.beginTransaction().add(R.id.nextid,fragment).commit();



        next = findViewById(R.id.nextid);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverAlloted.this, MainActivity.class);
                startActivity(intent);





            }
        });



}}
