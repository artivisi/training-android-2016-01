package com.artivisi.android.aplikasipembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.artivisi.android.aplikasipembayaran.R;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class PengaturanFragment extends Fragment {

    private View rootView;
    private EditText etNoHp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.pengaturan_layout, container, false);
        etNoHp = (EditText) rootView.findViewById(R.id.etNoHp);
        etNoHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Halo", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
