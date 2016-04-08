package com.artivisi.android.aplikasipembayaran.service;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.artivisi.android.aplikasipembayaran.dto.PageProduk;
import com.artivisi.android.aplikasipembayaran.dto.Produk;
import com.artivisi.android.aplikasipembayaran.restclient.PembayaranRestClient;
import com.google.android.gms.gcm.GcmListenerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endymuhardin on 4/6/16.
 */
public class MyGcmListenerService extends GcmListenerService {
    private static final String TAG = "MyGcm";
    List<Produk> list = new ArrayList<>();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i(TAG, "From : " + from);
        if(from.startsWith("/topics/produk")) {
            Log.i(TAG, "Isi : "+data.getString("update"));

            getProduk();
        }
    }

    private void getProduk() {
        new AsyncTask<Void, Void, PageProduk>(){

            @Override
            protected PageProduk doInBackground(Void... voids) {
                PembayaranRestClient client = new PembayaranRestClient
                        ("http://192.168.100.5:8080/");
                try {
                    return client.getSemuaProduk();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(PageProduk pageProduk) {
                Log.i(TAG, "Update Berhasil");

                if(pageProduk != null) {
                    list = pageProduk.getContents();
                    Log.i("produk", "" + pageProduk.getNumberOfElements());
                }
            }
        }.execute();
    }
}
