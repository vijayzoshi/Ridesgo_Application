package com.example.xml;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class History extends Fragment {


    private TextView mtextview;
    String data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history,container,false);

        mtextview = view.findViewById(R.id.textView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


         data =    dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Student Name").getValue(String.class);



                if(data == null) {

                    mtextview.setText("No Subscription");


                }else {

                    mtextview.setText("Subscribed");
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view ;

    }
}
