package com.artivisi.android.aplikasipembayaran;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.artivisi.android.aplikasipembayaran.service.GcmRegistrationIntentService;

import org.springframework.web.client.ResourceAccessException;

public class LoginActivity extends AppCompatActivity {

    private String tag = "LoginActivity";
    Button btn;
    EditText etUsername, etPassword;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "on start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "on Destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "on stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "on pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "on resume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);

            }
        });

        Intent intent = new Intent(this, GcmRegistrationIntentService.class);
        startService(intent);
    }

    private void login(String username, String password){

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
                PembayaranRestClient client = new PembayaranRestClient();

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
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(LoginActivity.this)
                                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                                    .setContentTitle("Aplikasi Pembayaran")
                                    .setContentText("Login Sukses");

                    Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                    intent.putExtra("nama", genericResponse.getData().get("fullname").toString());
                    intent.putExtra("email", genericResponse.getData().get("email").toString());

                    //startActivity(intent);

                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(LoginActivity.this);
                    stackBuilder.addNextIntent(intent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    stackBuilder.addNextIntent(intent);
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    int idNotifikasi = 100;
                    mNotificationManager.notify(idNotifikasi, mBuilder.build());

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
