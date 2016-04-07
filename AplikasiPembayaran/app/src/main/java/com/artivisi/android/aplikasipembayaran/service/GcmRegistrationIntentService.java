package com.artivisi.android.aplikasipembayaran.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.artivisi.android.aplikasipembayaran.R;
import com.artivisi.android.aplikasipembayaran.restclient.PembayaranRestClient;
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

            Log.i(TAG, "Token = " + token);
            String username = intent.getStringExtra("username");
            String serverUrl = intent.getStringExtra("serverUrl");
            updateToken(serverUrl, username, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateToken(final String url, final String username, final String token) {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                PembayaranRestClient client = new PembayaranRestClient(url);
                try {
                    client.updateToken(username, token);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.i(TAG, "Update Berhasil");
            }
        }.execute();
    }

}
