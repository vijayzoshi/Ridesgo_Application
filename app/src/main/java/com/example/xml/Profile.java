package com.example.xml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {

    private Button subscription,studentdetails;
    private Button driverdetails;
    private Button logout, customersupport;
    private TextView accountname, phoneno;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view =  inflater.inflate(R.layout.user,container,false);

        accountname = view.findViewById(R.id.nameid);
        phoneno = view.findViewById(R.id.phonenoid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            accountname.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Account Name").getValue(String.class));

                phoneno.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Phone Number").getValue(String.class));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        studentdetails = view.findViewById(R.id.studentdetailsIid);

        studentdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), StudentDetails.class);
                startActivity(intent);


            }
        });

        subscription= view.findViewById(R.id.subscriptionid);
        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Subscription.class);
                startActivity(intent);


            }
        });

        customersupport = view.findViewById(R.id.customersupportid);
        customersupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerSupport.class);
                startActivity(intent);
            }
        });



        driverdetails = view.findViewById(R.id.driverdetailsid);

        driverdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DriverDetails.class);
                startActivity(intent);
            }
        });


        logout = view.findViewById(R.id.logoutid);
        logout.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), PhoneLoginActivity.class);
                startActivity(intent);


            }
        });

        return view;
    }
}
