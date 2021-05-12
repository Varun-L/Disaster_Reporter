package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AboutAppActivity extends AppCompatActivity {

    /**
     *
     * AboutAppActivity - This Activity displays the About Page of the App.
     * Grabs the Background Color and Text color from shared preferences.
     * Set TextSize and Populates Menu for other options.
     * */

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about_app:
                Toast.makeText(this, "You can Report Disasters using the App", Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.sign_out:
                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(AboutAppActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    SharedPreferences sharedPreferences12 = getSharedPreferences("UP1", MODE_PRIVATE);
                    SharedPreferences.OnSharedPreferenceChangeListener listener3 = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences12, String s) {
                            startActivity(new Intent(AboutAppActivity.this, MainActivity.class));
                            finish();

                        }
                    };
                    sharedPreferences12.registerOnSharedPreferenceChangeListener(listener3);
                    SharedPreferences.Editor myEdit3 = sharedPreferences12.edit();
                    myEdit3.putBoolean("cup_login", false).apply();
                }
            case R.id.reported_eve:
                startActivity(new Intent(this, View_DEve.class));
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

}