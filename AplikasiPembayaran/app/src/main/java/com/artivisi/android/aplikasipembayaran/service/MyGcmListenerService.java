package com.artivisi.android.aplikasipembayaran.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.artivisi.android.aplikasipembayaran.R;
import com.artivisi.android.aplikasipembayaran.dto.PageProduk;
import com.artivisi.android.aplikasipembayaran.dto.Produk;
import com.artivisi.android.aplikasipembayaran.restclient.PembayaranRestClient;
import com.google.android.gms.gcm.GcmListenerService;

import java.text.SimpleDateFormat;
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

    private String getServerUrlFromSharedPref() {
        SharedPreferences sp =
                getSharedPreferences(getString(R.string.sp_key),
                        Context.MODE_PRIVATE);

        String serverUrlSP = sp.getString(getString(R.string.sp_key_url), "http://192.168.1.1");
        return serverUrlSP;
    }

    private void getProduk() {
        new AsyncTask<Void, Void, PageProduk>(){

            @Override
            protected PageProduk doInBackground(Void... voids) {
                PembayaranRestClient client = new PembayaranRestClient
                        (getServerUrlFromSharedPref());
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
                    list = pageProduk.getContent();
                    Log.i("produk", "" + pageProduk.getNumberOfElements());
                    Log.i("size ", "" + pageProduk.getContent().size());

                    insertIntoDb(pageProduk.getContent());
                }
            }
        }.execute();
    }

    private void insertIntoDb(List<Produk> list) {
        SimpleDateFormat fulltime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        AppDB db = new AppDB(this.getApplicationContext());
        db.open();
        for (Produk p : list) {
            db.insertProduk(
                    p.getId(),
                    p.getKode(),
                    p.getNama(),
                    fulltime.format(p.getTerakhirUpdate()));
        }
        db.close();
    }
}
