package com.example.xml;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Firebase........
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        // Reading Data from Firebase...........
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String driverid = dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DRIVER DETAILS").child("DRIVER ID").getValue(String.class);


                String data1=   dataSnapshot.child("Driver").child(driverid).child("Latitude").getValue(String.class);
                String data2=    dataSnapshot.child("Driver").child(driverid).child("Longitude").getValue(String.class);
                // Adding Marker ..........
               LatLng location = new LatLng(Double.parseDouble(data1) , Double.parseDouble(data2) );
             //   LatLng location = new LatLng(  27.173891, 78.042068 );
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(location).title("Hmmmmm").icon(BitmapDescriptorFactory.fromResource(R.drawable.dar)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));



                //  Toast.makeText(getApplicationContext(), data1 + data2,Toast.LENGTH_LONG).show();

            }
        // Add a marker in Sydney and move the camera
     //   LatLng sydney = new LatLng(-34, 151);
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
