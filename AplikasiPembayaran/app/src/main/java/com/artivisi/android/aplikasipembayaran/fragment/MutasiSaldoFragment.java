package com.artivisi.android.aplikasipembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artivisi.android.aplikasipembayaran.R;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class MutasiSaldoFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mutasi_saldo_layout, container, false);
        return rootView;
    }

}
