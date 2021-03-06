package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    /**
     * MainActivity2 provides two Buttons ReportDisaster and View Reported Disasters.
     * According to User Selection he will be redirected to Activities.
     * */

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RelativeLayout rl = findViewById(R.id.user_landing_page_a2);
        b1 = findViewById(R.id.reportDisasterButton);
        b2=findViewById(R.id.viewReportedDisasterButton);



        TextView[] ta = {};
        getSupportActionBar().setTitle("Report/View");
        SharedPreferences sh = getSharedPreferences("UP", MODE_PRIVATE);
        float a2 = (float)sh.getInt("size", 17);
        int map_style_file =sh.getInt("color",0);
        new Helpers11().setColorB(rl,map_style_file,ta);
        new Helpers11().setButtonBF(b1,map_style_file);
        new Helpers11().setButtonBF(b2,map_style_file);

        b1.setTextSize(a2);
        b2.setTextSize(a2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,ReportDisasterActivity.class));
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,View_DEve.class));
            }
        });

    TextView t9u = findViewById(R.id.textView6);
    t9u.setTextSize(a2-3);
    t9u.setTextColor(Color.parseColor(new Helpers11().getSpinBack(map_style_file)));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.about_app:
                startActivity(new Intent(MainActivity2.this, AboutAppActivity.class));
                return(true);
            case R.id.reported_eve:
                startActivity(new Intent(MainActivity2.this,View_DEve.class));
                return (true);
            case R.id.sign_out:
                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    finish();
                }
                else {
                    SharedPreferences sharedPreferences12 = getSharedPreferences("UP1", MODE_PRIVATE);
                    SharedPreferences.OnSharedPreferenceChangeListener listener3 = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences12, String s) {
                            startActivity(new Intent(MainActivity2.this, MainActivity.class));
                            finish();

                        }
                    };
                    sharedPreferences12.registerOnSharedPreferenceChangeListener(listener3);
                    SharedPreferences.Editor myEdit3 = sharedPreferences12.edit();
                    myEdit3.putBoolean("cup_login", false).apply();
                }

        }

        return(super.onOptionsItemSelected(item));
    }

}