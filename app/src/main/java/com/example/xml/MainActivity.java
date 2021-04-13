package com.example.xml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String  data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);

                String  data2 =    dataSnapshot.child("Driver").child("4Q3kUd5WtEgqPmTDhS9ZqP2e2s02").child("Tracking").getValue(String.class);

                if(Integer.parseInt(data)== 1){

                    if(Integer.parseInt(data2)== 1){


                        final loadingDialog loadingDialog = new loadingDialog(MainActivity.this);
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismisDialog();
                            }
                        },5000);






                    }else{
 final loadingDialog loadingDialog = new loadingDialog(MainActivity.this);
                        loadingDialog.startLoadingDialog();
                         Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismisDialog();
                            }
                        },5000);
                    }


                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/


        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation_view);
        bottomnav.setOnNavigationItemSelectedListener(navListener);


       // FirebaseDatabase database = FirebaseDatabase.getInstance();
      //  DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


               String data = dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
             //   String data2 = "True";

            /*   if(Integer.parseInt(data)== 1){
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();

            }
                else {



                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Homesubscribed2()).commit();
                }
*/

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              String data = dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);
               // String data2 = "True";
         //     int Track = Integer.valueOf(dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Tracking").getValue(String.class));




                Fragment selectedfragment = null;
                switch (menuItem.getItemId()){

                    case R.id.home :
                      selectedfragment = new Home();
                        break;
                    /*   if(Integer.parseInt(data)== 1) {
                           selectedfragment = new Home();
                           break;
                        }
                        else {
                           selectedfragment = new Homesubscribed2();
                           break;

                        }

*/



//                    case R.id.history :
  //                      selectedfragment = new History();
    //                    break;

                    case R.id.account :
                        selectedfragment = new Profile();
                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return true;
    }
};



}
