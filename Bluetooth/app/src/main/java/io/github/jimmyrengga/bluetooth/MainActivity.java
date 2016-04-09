package io.github.jimmyrengga.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btnScan, btnConnect;
    private Spinner spnBluetooth;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = (Button) findViewById(R.id.btnScan);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        spnBluetooth = (Spinner) findViewById(R.id.spinner);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBluetoothAdapter == null) {
            Toast.makeText(MainActivity.this,
                    "Bluetooth not supported", Toast.LENGTH_SHORT).show();
        } else {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if (pairedDevices != null) {
                mDeviceList.addAll(pairedDevices);

                updateDeviceList();
            }
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter.startDiscovery();
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });

    }

    private void createBond(BluetoothDevice device) throws Exception {

        try {
            Class<?> cl 	= Class.forName("android.bluetooth.BluetoothDevice");
            Class<?>[] par 	= {};

            Method method 	= cl.getMethod("createBond", par);

            method.invoke(device);

        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }

    private void connect() {
        BluetoothDevice device = mDeviceList.get(spnBluetooth.getSelectedItemPosition());

        if (device.getBondState() == BluetoothDevice.BOND_NONE) {
            try {
                createBond(device);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Failed to pair device", Toast.LENGTH_SHORT).show();

                return;
            }
        }
    }

    private void updateDeviceList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getArray(mDeviceList));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnBluetooth.setAdapter(adapter);
        spnBluetooth.setSelection(0);
    }

    private String[] getArray(ArrayList<BluetoothDevice> data) {
        String[] list = new String[0];

        if (data == null) return list;

        int size	= data.size();
        list		= new String[size];

        for (int i = 0; i < size; i++) {
            list[i] = data.get(i).getName();
        }

        return list;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state 	= intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                if (state == BluetoothAdapter.STATE_ON) {
                } else if (state == BluetoothAdapter.STATE_OFF) {
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mDeviceList = new ArrayList<BluetoothDevice>();

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                updateDeviceList();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                mDeviceList.add(device);

                Toast.makeText(MainActivity.this,"Found device " + device.getName(), Toast.LENGTH_SHORT).show();
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);

                if (state == BluetoothDevice.BOND_BONDED) {
                    Toast.makeText(MainActivity.this,"Paired", Toast.LENGTH_SHORT).show();

                    connect();
                }
            }
        }
    };

}
