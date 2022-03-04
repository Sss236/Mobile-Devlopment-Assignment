package edu.neu.madcourse.numad21fa_yaqianyang;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class locationActivity extends AppCompatActivity {
    private TextView latitude;
    private TextView longitude;
    private Button btLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        latitude=findViewById(R.id.latitude);
        longitude=findViewById(R.id.longitude);
        btLocation=findViewById(R.id.bt_Location);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(locationActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(locationActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},44);
                    }
                }
            });

        }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {

            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null){

                    latitude.setText("Latitude: " + String.valueOf(location.getLatitude()));
                    longitude.setText("Longitude: " + String.valueOf(location.getLongitude()));
                    Log.e("loc","KSHFLFJDLH");
//                    try {
//                        Geocoder geocoder = new Geocoder(locationActivity.this,
//                                Locale.getDefault());
//                        List<Address> addresses = geocoder.getFromLocation(
//                                location.getLatitude(),location.getLongitude(),1
//                        );
//                        latitude.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Latitude :<b/><br></font>"
//                                + addresses.get(0).getLatitude()
//                        ));
//                        longitude.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Longitude :<b/><br></font>"
//                                        + addresses.get(0).getLongitude()
//                        ));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                } else {

                    LocationRequest locationRequest = new LocationRequest()
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                            .setInterval(10000)
                            .setFastestInterval(1000)
                            .setNumUpdates(1);

                    LocationCallback locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            android.location.Location location1 = locationResult.getLastLocation();
                            latitude.setText("Latitude: " + String.valueOf(location1.getLatitude()));
                            longitude.setText("Longitude: " + String.valueOf(location1.getLongitude()));
                        }
                    };
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                            locationCallback, Looper.myLooper());
                }

            }
        });
    }
}

