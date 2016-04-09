package io.github.jimmyrengga.gps;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by jimmy on 09/04/16.
 */
public class GpsHandler extends Service implements LocationListener {

    LocationManager locationManager;
    Location location;
    Context _ctx;

    double latitude;
    double longitude;

    private boolean isGPSEnable = false;
    private boolean isNetworkEnable = false;
    private boolean canGetLocation = false;
    private static final long MIN_JARAK_GPS_UPDATE = 10;
    // GPS akan update pada waktu interval
    private static final long MIN_WAKTU_GPS_UPDATE = 1000 * 60 * 1;

    public boolean isCanGetLocation() {
        return canGetLocation;
    }


    public GpsHandler(Context ctx) {
        this._ctx = ctx;
        getLocation();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    private Location getLocation() {
        try {
            locationManager = (LocationManager) _ctx
                    .getSystemService(LOCATION_SERVICE);
            // cek GPS status
            isGPSEnable = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // cek status koneksi
            isNetworkEnable = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnable && !isNetworkEnable) {
                // tidak ada koneksi ke GPS dan Jaringan
            } else {
                // bisa dapatkan lokasi
                canGetLocation = true;
                // cek apakah koneksi internet bisa ?
                if (isNetworkEnable) {
                    // ambil posisi berdasarkan Network

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_WAKTU_GPS_UPDATE,
                            MIN_JARAK_GPS_UPDATE, this);
                    if (locationManager != null) {
                        // ambil posisi terakhir user menggunakan Network
                        location = locationManager.getLastKnownLocation(LocationManager
                                .NETWORK_PROVIDER);
                        // jika lokasi berhasil didapat
                        if (location != null) {
                            // ambil latitude
                            latitude = location.getLatitude();
                            // ambil longitude
                            longitude = location.getLongitude();
                        }
                    }
                }
                // jika gps bisa digunakan
                if (isGPSEnable)
                {
                    if (location == null)
                    {
                        // ambil posisi berdasar GPS
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                MIN_WAKTU_GPS_UPDATE,
                                MIN_JARAK_GPS_UPDATE, this);
                        if (locationManager != null)
                        {
                            // dapatkan posisi terakhir user menggunakan GPS
                            location = locationManager.getLastKnownLocation(LocationManager
                                    .GPS_PROVIDER);
                            // jika lokasi berhasil didapat
                            if (location != null)
                            {
                                // ambil latitude
                                latitude = location.getLatitude();
                                // ambil longitude
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void showSettingsAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(_ctx);
        builder.setTitle("GPS Setting");
        builder.setMessage("Aktifkan GPS ?");
        builder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                _ctx.startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
}
