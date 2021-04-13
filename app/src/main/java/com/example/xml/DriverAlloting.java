package com.example.xml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DriverAlloting extends AppCompatActivity {

   // Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_alloting);


   /*     next = findViewById(R.id.button2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DriverAlloting.this,DriverAlloted.class);
                startActivity(intent);
            }
        });
*/
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String data=   dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS ").child("DRIVER ALLOTED").getValue(String.class);

               if(data == "True"){
  /* next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DriverAlloting.this,DriverAlloted.class);
                startActivity(intent);
            }
        });
*/
                   Intent intent = new Intent(DriverAlloting.this,DriverAlloted.class);
                   startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }
}
