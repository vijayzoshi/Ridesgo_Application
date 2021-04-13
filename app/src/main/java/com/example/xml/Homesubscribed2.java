package com.example.xml;

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

public class Homesubscribed2 extends Fragment {



    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;



    private Button mButton;
    private Button track;

    TextView test;

    //  String data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homesubscribed, container, false);
        track = view.findViewById(R.id.track);
        mButton = view.findViewById(R.id.requestschoolcabid);
        test = view.findViewById(R.id.textView12);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String  data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ALLOTED").getValue(String.class);


if(Integer.parseInt(data)== 1){

    test.setText("yes");



}else{
    test.setText("no");
}






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        return view;


    }
    }
