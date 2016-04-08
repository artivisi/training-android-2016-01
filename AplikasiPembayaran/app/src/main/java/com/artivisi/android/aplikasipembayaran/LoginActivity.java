package com.artivisi.android.aplikasipembayaran;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.artivisi.android.aplikasipembayaran.dto.GenericResponse;
import com.artivisi.android.aplikasipembayaran.exception.GagalLoginException;
import com.artivisi.android.aplikasipembayaran.restclient.PembayaranRestClient;
import com.artivisi.android.aplikasipembayaran.service.AppDB;
import com.artivisi.android.aplikasipembayaran.service.GcmRegistrationIntentService;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private String tag = "LoginActivity";
    Button btn;
    EditText etUsername, etPassword, etServerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etServerUrl = (EditText) findViewById(R.id.etServerUrl);
        etServerUrl.setText(getServerUrlFromSharedPref());

        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String serverUrl = etServerUrl.getText().toString();

                login(username, password, serverUrl);
            }
        });

    }

    private String getServerUrlFromSharedPref() {
        SharedPreferences sp =
                getSharedPreferences(getString(R.string.sp_key),
                        Context.MODE_PRIVATE);

        String serverUrlSP = sp.getString(getString(R.string.sp_key_url), "http://192.168.1.1");
        return serverUrlSP;
    }

    private void updateServerUrlInSharedPref(String url) {
        SharedPreferences sp =
                getSharedPreferences(getString(R.string.sp_key),
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(getString(R.string.sp_key_url), url);
        editor.commit();
    }

    private void login(final String username, String password, final String serverUrl){

        new AsyncTask<String, Void, GenericResponse>(){

            ProgressDialog progressDialog;
            String pesanError;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(LoginActivity.this,
                        "Logging in",
                        "mengecek username dan password",
                        true);
            }

            @Override
            protected GenericResponse doInBackground(String... params) {
                PembayaranRestClient client = new PembayaranRestClient(serverUrl);

                try {
                    GenericResponse hasil = client.login(params[0], params[1]);
                    Log.i(tag, "Sukses : " + hasil.isSuccess());
                    if (hasil.isSuccess()) {
                        Log.i(tag, "Email : " + hasil.getData().get("email"));
                    } else {
                        Log.i(tag, "Error : " + hasil.getData().get("errormessage"));
                    }
                    return hasil;

                } catch (GagalLoginException err){
                    Log.i(tag, "Koneksi ke server gagal");
                    pesanError = err.getMessage();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(GenericResponse genericResponse) {

                progressDialog.dismiss();

                if(genericResponse == null){
                    Toast.makeText(LoginActivity.this,
                            pesanError,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(genericResponse.isSuccess()){
                    updateServerUrlInSharedPref(serverUrl);

                    Intent gcmRegServiceIntent = new Intent(LoginActivity.this, GcmRegistrationIntentService.class);
                    gcmRegServiceIntent.putExtra("username", username);
                    gcmRegServiceIntent.putExtra("serverUrl", serverUrl);
                    startService(gcmRegServiceIntent);

                    Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                    intent.putExtra("nama", genericResponse.getData().get("fullname").toString());
                    intent.putExtra("email", genericResponse.getData().get("email").toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,
                            genericResponse.getData()
                                    .get("errormessage").toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }


            }
        }.execute(username, password);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
