
package com.example.xml;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {


    private EditText name;
    private Button mButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (EditText) findViewById(R.id.name);
        mButton= (Button) findViewById(R.id.requestschoolcabid);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             /*   FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference();


                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                myRef.child("User").child(currentuser).child("Account Name").setValue(name.getText().toString());
*/



                Intent intent = new Intent(DetailsActivity.this, FillStudentDetails.class);
                startActivity(intent);


            }
        });



    }
}

