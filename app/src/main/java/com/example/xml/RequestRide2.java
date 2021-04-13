package com.example.xml;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class RequestRide2 extends AppCompatActivity {

    ImageButton  calend;
    TextView Date;
    Button submit;
    String dateString;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ride2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Request Ride");
        actionBar.setDisplayHomeAsUpEnabled(true);


        calend = (ImageButton) findViewById(R.id.calenderid);
        Date = (TextView) findViewById(R.id.dateid);
        submit = (Button) findViewById(R.id.submitid);



        calend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RequestRide2.this, DriverAlloting.class);
                startActivity(intent);

                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("Driver Photo").setValue("");
                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("Driver DL").setValue("");
                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("Driver Name").setValue("");
                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("Driver exp").setValue("");
                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("DRIVER ALLOTED").setValue("");
                myRef.child("User").child(currentuser).child("DRIVER DETAILS").child("DRIVER ID").setValue("");









            }
        });


    }


   public void handleDateButton(){

        Calendar calender = Calendar.getInstance();

        int YEAR= calender.get(Calendar.YEAR);
        int MONTH= calender.get(Calendar.MONTH);
        int DAY= calender.get(Calendar.DATE);

       DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dateString = dayOfMonth + "/"+ month+ "/" + year;
               Date.setText(dateString);

         //      myRef.child("User").child(currentuser).child("Pack").child("Starting Date").setValue(dateString);
           //    myRef.child("User").child(currentuser).child("Pack").child("Subscription").setValue("3 Months");






/*
               HashMap<String,Object> map1 = new HashMap<>();
               map1.put("Driver photo","");
               map1.put("Driver DL","");
               map1.put("Driver Name","");
               map1.put("Driver exp","");
               map1.put("DRIVER ALLOTED","");
*/

/*  HashMap<String,Object> map2 = new HashMap<>();

               map2.put("Starting Date", dateString);
               map2.put("Subscription","3 Months");
*/
             //  myRef.child("User").child(currentuser).child("DRIVER DETAILS").updateChildren(map1);

           }
       }, YEAR,MONTH,DAY);

       datePickerDialog.show();

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
