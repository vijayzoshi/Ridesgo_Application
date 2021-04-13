package com.example.xml;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RequestRide extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();


    private Button submit;
    private EditText studentname, schoolname, homeadress, studentclass;

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ride);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.student_details);
        actionBar.setDisplayHomeAsUpEnabled(true);

        submit = findViewById(R.id.editdetailsid);
        studentname = findViewById(R.id.studentnameid);
        schoolname = findViewById(R.id.schoolnameid);
        studentclass = findViewById(R.id.classid);
        homeadress= findViewById(R.id.homeaddressid);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(studentname.getText().toString() ) || TextUtils.isEmpty(studentclass.getText().toString() ) || TextUtils.isEmpty(schoolname.getText().toString() ) ||TextUtils.isEmpty(homeadress.getText().toString() )  )
                {
                    Toast.makeText(RequestRide.this, "Enter Complete Details", Toast.LENGTH_LONG).show();
                }else{





    /*              String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    HashMap<String,Object> map1 = new HashMap<>();
                    map1.put("Student Name", studentname.getText().toString());
                    map1.put("Student Class",studentclass.getText().toString());
                    map1.put("School Name",schoolname.getText().toString());
                    map1.put("Home Address",homeadress.getText().toString());


                    myRef.child("User").child(currentuser).updateChildren(map1);
*/
                    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    myRef.child("User").child(currentuser).child("Student Name").setValue(studentname.getText().toString());

                    Intent intent = new Intent(RequestRide.this, RequestRide2.class);
                    startActivity(intent);

                }

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
