package com.example.xml;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Subscription extends AppCompatActivity {

    Button requestnow;
    View nosubcription;
    View subcription;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Subcription");
        actionBar.setDisplayHomeAsUpEnabled(true);



        requestnow = findViewById(R.id.requestnowid);
        nosubcription= findViewById(R.id.nosub);
        subcription = findViewById(R.id.sub);


       nosubcription.setVisibility(View.VISIBLE);
     subcription.setVisibility(View.GONE);

     requestnow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Subscription.this, RequestRide.class);
             startActivity(intent);
         }
     });
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll2.setVisibility(View.VISIBLE);
                ll1.setVisibility(View.GONE);

            }
        });
*/


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String data=   dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);



             /*   if(Integer.parseInt(data)== 1){

                    Intent intent = new Intent(Subscription.this, MainActivity.class);
                    startActivity(intent);
                }*/
                if(Integer.parseInt(data)== 1){

                  //  Intent intent = new Intent(Subscription.this, MainActivity.class);
                  //  startActivity(intent);

                    subcription.setVisibility(View.VISIBLE);
                     nosubcription.setVisibility(View.GONE);
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
