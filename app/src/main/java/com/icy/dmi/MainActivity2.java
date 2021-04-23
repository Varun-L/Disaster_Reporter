package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {


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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,GMaps1.class));
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,View_DEve.class));
            }
        });



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
                startActivity(new Intent(MainActivity2.this,SettingsActivity.class));
                return(true);
/*            case R.id.exit_app:
                finish();
                System.exit(0);
                return(true);*/
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                finish();
                return (true);
            case R.id.reported_eve:
                startActivity(new Intent(MainActivity2.this,View_DEve.class));
                return (true);
           /* case R.id.settings_user:
                startActivity(new Intent(GMaps1.this,SettingsActivity.class)); */
        }
        return(super.onOptionsItemSelected(item));
    }

}