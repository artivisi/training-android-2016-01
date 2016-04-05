package com.artivisi.android.aplikasipembayaran;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.artivisi.android.aplikasipembayaran.fragment.BayarFragment;
import com.artivisi.android.aplikasipembayaran.fragment.DashboardFragment;
import com.artivisi.android.aplikasipembayaran.fragment.FavoritFragment;
import com.artivisi.android.aplikasipembayaran.fragment.HistoriTransaksiFragment;
import com.artivisi.android.aplikasipembayaran.fragment.MutasiSaldoFragment;
import com.artivisi.android.aplikasipembayaran.fragment.PengaturanFragment;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class MainActivity extends AppCompatActivity {

    private String[] menus = {"Dashboard", "Bayar", "Favorit", "History Transaksi", "Mutasi Saldo", "Pengaturan", "Keluar"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    DashboardFragment dashboardFragment = new DashboardFragment();
    FavoritFragment favoritFragment = new FavoritFragment();
    BayarFragment bayarFragment = new BayarFragment();
    HistoriTransaksiFragment historiTransaksiFragment = new HistoriTransaksiFragment();
    MutasiSaldoFragment mutasiSaldoFragment = new MutasiSaldoFragment();
    PengaturanFragment pengaturanFragment = new PengaturanFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menus));
        // Set the list's click listener
        listView.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch(position) {
                case 0:
//                    Toast.makeText(getApplicationContext(), "menu 1", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, dashboardFragment);
                    break;
                case 1:
//                    Toast.makeText(getApplicationContext(), "menu 2", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, bayarFragment);
                    break;
                case 2:
//                    Toast.makeText(getApplicationContext(), "menu 3", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, favoritFragment);
                    break;
                case 3:
//                    Toast.makeText(getApplicationContext(), "menu 3", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, historiTransaksiFragment);
                    break;
                case 4:
//                    Toast.makeText(getApplicationContext(), "menu 3", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, mutasiSaldoFragment);
                    break;
                case 5:
//                    Toast.makeText(getApplicationContext(), "menu 3", Toast.LENGTH_SHORT).show();
                    fragmentTransaction.replace(R.id.content_frame, pengaturanFragment);
                    break;
                case 6:
                    Toast.makeText(getApplicationContext(), "Menu Keluar", Toast.LENGTH_SHORT).show();
//                    fragmentTransaction.replace(R.id.content_frame, favoritFragment);
                    break;
                default:
                    fragmentTransaction.replace(R.id.content_frame, dashboardFragment);
                    break;
            }

            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        }
    }
}
