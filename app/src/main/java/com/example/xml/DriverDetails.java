package com.example.xml;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DriverDetails extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();
   DatabaseReference getDriverImage = myRef.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("Driver Photo");
    DatabaseReference getDriverDl = myRef.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("Driver DL");




    private TextView drivername;
    private TextView driverexp;
    private ImageView driverimage;
    private ImageView driverdl;
    View nosubcription;
    View subcription;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        nosubcription= findViewById(R.id.nosub);
        subcription = findViewById(R.id.sub);


        nosubcription.setVisibility(View.VISIBLE);
        subcription.setVisibility(View.GONE);


        driverimage = findViewById(R.id.driverimageid);
        drivername = findViewById(R.id.nameid);
        driverexp= findViewById(R.id.expid);
        driverdl = findViewById(R.id.dlimageid);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Driver Details");
        actionBar.setDisplayHomeAsUpEnabled(true);


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



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                drivername.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("Driver Name").getValue(String.class));
                String exp =  dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("Driver exp").getValue(String.class);

                driverexp.setText( exp + "yrs Experienced");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








      getDriverImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // relative path and getting in the link variable
                String link = dataSnapshot.getValue(String.class);

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link).into(driverimage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        getDriverDl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // relative path and getting in the link variable
                String link = dataSnapshot.getValue(String.class);

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link).into(driverdl);
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
