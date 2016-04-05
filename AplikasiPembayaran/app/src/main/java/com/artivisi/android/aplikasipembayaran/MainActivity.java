package com.artivisi.android.aplikasipembayaran;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class MainActivity extends Activity {

    private String[] menus = {"Menu 1", "Menu 2", "Menu 3"};
    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menus));
        // Set the list's click listener
//        listView.setOnItemClickListener(new DrawerItemClickListener());
    }
}
