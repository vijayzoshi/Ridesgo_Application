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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDetails extends AppCompatActivity {

    TextView studentname, studentclass, schoolname,homeaddress;
    Button editdetails;
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_details);
        ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle(R.string.student_details);
        actionBar.setDisplayHomeAsUpEnabled(true);

        studentname =  findViewById(R.id.studentnameid);
        studentclass =  findViewById(R.id.classid);
        schoolname =  findViewById(R.id.schoolnameid);
        homeaddress =  findViewById(R.id.homeaddressid);
        editdetails= findViewById(R.id.editdetailsid);

        editdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetails.this, FillStudentDetails.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentname.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Student Name").getValue(String.class));

                studentclass.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Student Class").getValue(String.class));

                schoolname.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("School Name").getValue(String.class));

                homeaddress.setText( dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Home Address").getValue(String.class));

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
