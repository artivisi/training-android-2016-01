package com.artivisi.android.aplikasipembayaran.service;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by endymuhardin on 4/6/16.
 */
public class MyGcmListenerService extends GcmListenerService {
    private static final String TAG = "MyGcm";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i(TAG, "From : " + from);
        Log.i(TAG, "Isi : "+data.getString("pesan"));
    }
}
