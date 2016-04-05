package com.artivisi.android.aplikasipembayaran;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CekTagihanActivity extends AppCompatActivity {

    Button btnPeriksa;
    Spinner spinner;
    EditText etNomorPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_tagihan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinner);
        etNomorPelanggan = (EditText) findViewById(R.id.nopelanggan);

        btnPeriksa = (Button) findViewById(R.id.btnPeriksa);
        btnPeriksa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CekTagihanActivity.this, MainActivity.class);
                intent.putExtra("produk", (String) spinner.getSelectedItem());
                intent.putExtra("nomor", etNomorPelanggan.getText().toString());

                startActivity(intent);
            }
        });

    }

}
