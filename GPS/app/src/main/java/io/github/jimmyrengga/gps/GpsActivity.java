package io.github.jimmyrengga.gps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                if(gpsHandler.isCanGetLocation()) {
                    Toast.makeText(getApplicationContext(),
                            "Lokasi anda latitude: " + gpsHandler.getLatitude() +
                                    ", longitude: "+ gpsHandler.getLongitude(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    gpsHandler.showSettingsAlert();
                }
            }
        });
    }
}
