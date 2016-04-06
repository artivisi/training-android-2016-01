package com.artivisi.android.aplikasipembayaran.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.artivisi.android.aplikasipembayaran.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by endymuhardin on 4/6/16.
 */
public class GcmRegistrationIntentService extends IntentService {

    private static final String TAG = "GcmRegIntSvc";

    public GcmRegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "Token = "+token);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
