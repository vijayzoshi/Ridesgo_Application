package com.example.xml;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends Fragment {



    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;



    private Button mButton, button;
    private Button track;
    View nosub;
    View sub;
    View track2;
    CheckBox checkin;
    CheckBox checkout;

    //  String data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        track = view.findViewById(R.id.track);
        mButton = view.findViewById(R.id.requestschoolcabid);
        button=view.findViewById(R.id.button);
       nosub = view.findViewById(R.id.nosub);
      sub= view.findViewById(R.id.sub);
      track2= view.findViewById(R.id.trackid);

      checkin=  view.findViewById(R.id.checkin);
      checkout= view.findViewById(R.id.checkout);

     /*   final loadingDialog loadingDialog = new loadingDialog(Home.this);
        loadingDialog.startLoadingDialog();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismisDialog();
            }
        },5000);
*/

      nosub.setVisibility(View.VISIBLE);
      sub.setVisibility(View.GONE);
      track2.setVisibility(View.GONE);


track.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);

    }
});


       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String  data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);

                String  data2 =    dataSnapshot.child("Driver").child("4Q3kUd5WtEgqPmTDhS9ZqP2e2s02").child("Tracking").getValue(String.class);

                if(Integer.parseInt(data)== 1){

                    if(Integer.parseInt(data2)== 1){

                        sub.setVisibility(View.VISIBLE);
                        nosub.setVisibility(View.GONE);
                        track2.setVisibility(View.GONE);

                    }else{
                        sub.setVisibility(View.GONE);
                        nosub.setVisibility(View.GONE);
                        track2.setVisibility(View.VISIBLE);
                    }


                }else{
                    nosub.setVisibility(View.VISIBLE);
                    sub.setVisibility(View.GONE);
                    track2.setVisibility(View.GONE);


                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RequestRide.class);
                startActivity(intent);
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String  data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Checked in").getValue(String.class);


                if(Integer.parseInt(data)== 1){

                    checkin.setChecked(true);

                } else{
                    checkin.setChecked(false);

                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String  data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Checked out").getValue(String.class);


                if(Integer.parseInt(data)== 1){

                    checkout.setChecked(true);

                } else{
                    checkout.setChecked(false);

                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;



    }



}
