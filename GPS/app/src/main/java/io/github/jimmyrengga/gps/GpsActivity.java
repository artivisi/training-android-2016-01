package io.github.jimmyrengga.gps;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GpsActivity extends AppCompatActivity {

    Button btnAccessLocation;
    GpsHandler gpsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        btnAccessLocation = (Button) findViewById(R.id.btnGetLocation);
        btnAccessLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpsHandler = new GpsHandler(GpsActivity.this);
                Location location = gpsHandler.getLocation();
                if(gpsHandler.isCanGetLocation()) {
                    if(location != null) {
                        Toast.makeText(getApplicationContext(),
                                "Lokasi anda latitude: " + gpsHandler.getLatitude() +
                                        ", longitude: "+ gpsHandler.getLongitude(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("GPS", "getLocation is null");
                    }
                } else {
                    gpsHandler.showSettingsAlert();
                }
            }
        });
    }
}
