package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends BActivity {

    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    t1= findViewById(R.id.label_for_about_app);

        getSupportActionBar().setTitle("About App");


        String st="\t\t About the App :"+
            "\tDisaster Reporter Application helps you to report about " +
            "the Disasters that have happened in the past and " +
            "warn about the disasters which are likely to occur in future." +
            " Users can also View the Reported Disasters.\n\n\n"+
            "\t\t\t\t © I3D Labs 2021 ";
        t1.setText(st);
        TextView[] ta = {t1};
        RelativeLayout rl = findViewById(R.id.rl_about_app);



        SharedPreferences sh = getSharedPreferences("UP", MODE_PRIVATE);
        float a2 = (float)sh.getInt("size", 17);
        int map_style_file =sh.getInt("color",0);
        new Helpers11().setColorB(rl,map_style_file,ta);
        subSetTextSize(ta,a2);


    }

    public void subSetTextSize(TextView[] ta,float size){

        ta[0].setTextSize(size);
       /* for (TextView t1:ta) {
            t1.setTextSize(size);
        }*/

    }
}