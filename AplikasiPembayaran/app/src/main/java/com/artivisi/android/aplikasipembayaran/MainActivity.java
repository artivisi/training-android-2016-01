package com.artivisi.android.aplikasipembayaran;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
    private ActionBarDrawerToggle drawerToggle;
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


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.left_drawer);

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menus));
        listView.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.menu,
                R.string.app_name,
                R.string.app_name
        ) {
            public void onDrawerClosed(View view) {

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch(position) {
                case 0:
                    fragmentTransaction.replace(R.id.content_frame, dashboardFragment);
                    break;
                case 1:
                    fragmentTransaction.replace(R.id.content_frame, bayarFragment);
                    break;
                case 2:
                    fragmentTransaction.replace(R.id.content_frame, favoritFragment);
                    break;
                case 3:
                    fragmentTransaction.replace(R.id.content_frame, historiTransaksiFragment);
                    break;
                case 4:
                    fragmentTransaction.replace(R.id.content_frame, mutasiSaldoFragment);
                    break;
                case 5:
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
