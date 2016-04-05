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

import com.artivisi.android.aplikasipembayaran.fragment.DashboardFragment;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class MainActivity extends AppCompatActivity {

    private String[] menus = {"Menu 1", "Menu 2", "Menu 3"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    DashboardFragment dashboardFragment = new DashboardFragment();

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
                    Toast.makeText(getApplicationContext(), "menu 2", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "menu 3", Toast.LENGTH_SHORT).show();
                    break;
            }

            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        }
    }
}
