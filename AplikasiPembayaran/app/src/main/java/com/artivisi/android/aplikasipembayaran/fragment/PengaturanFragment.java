package com.artivisi.android.aplikasipembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.artivisi.android.aplikasipembayaran.R;

/**
 * Created by endymuhardin on 4/5/16.
 */
public class PengaturanFragment extends Fragment {

    private View rootView;
    private CheckBox checkBox;
    private RadioButton rb1, rb2, rb3, rbSelected;
    private RadioGroup radioGroup;
    private Button btnSimpan;
    private EditText etEmail, etNoHp;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.pengaturan_layout, container, false);
        checkBox = (CheckBox) rootView.findViewById(R.id.checkBox);
        btnSimpan = (Button) rootView.findViewById(R.id.btnSimpanPengaturan);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGrp);
        etNoHp = (EditText) rootView.findViewById(R.id.editText2);
        etEmail = (EditText) rootView.findViewById(R.id.editText);
        spinner = (Spinner) rootView.findViewById(R.id.spinner2);

        int check = radioGroup.getCheckedRadioButtonId();

        rb1 = (RadioButton) rootView.findViewById(R.id.radioButton);
        rb2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
        rb3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
        Log.i("id Checked radio", String.valueOf(check));

        int idx = radioGroup.indexOfChild(rbSelected);
        rbSelected = (RadioButton) radioGroup.getChildAt(idx);
        etNoHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("edit text was", "clicked");
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("spinner", spinner.getSelectedItem().toString());
                Log.i("radio 1", String.valueOf(rb1.isChecked()));
                Log.i("radio 2", String.valueOf(rb2.isChecked()));
                Log.i("radio 3", String.valueOf(rb3.isChecked()));
                Log.i("radio 4", rbSelected.getText().toString());
                Log.i("check box ", checkBox.getText().toString());
                Log.i("Email ", etEmail.getText().toString());
                Log.i("No Hp ", etNoHp.getText().toString());
            }
        });

        return rootView;
    }
}
