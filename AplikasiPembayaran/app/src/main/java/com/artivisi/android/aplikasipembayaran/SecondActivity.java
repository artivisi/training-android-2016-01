package com.artivisi.android.aplikasipembayaran;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvJenisTagihan, tvDetailNoPel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagihan_detail_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");

        tvJenisTagihan = (TextView) findViewById(R.id.jenisTag);
        tvJenisTagihan.setText(nama);

        tvDetailNoPel = (TextView) findViewById(R.id.detailNoPel);
        tvDetailNoPel.setText(email);
    }

}
