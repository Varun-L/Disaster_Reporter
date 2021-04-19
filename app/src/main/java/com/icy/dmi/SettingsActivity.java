package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends BActivity {

    TextView t1;
    Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    t1= findViewById(R.id.label_for_textSize_spinner);
    s1= findViewById(R.id.spinner_textSizeSelectApp);

    s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(i==0){            }
            else if(i==1){}
            else if(i==2){}
            else if(i==3){}

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

    }
}