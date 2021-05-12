package com.icy.dmi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;

import java.util.Calendar;
import java.util.Date;



public class ReportDisasterActivity extends AppCompatActivity {


    /**
     * ReportDisasterActivity is the Main Key Activity for the App.
     * This Page helps the user to report disasters.
     * We provide 4 Spinners to the User.
     * Collect If this is an Incoming Disaster or an Previous Disaster,
     * Which Type of Disaster it is,
     * Severity of the Disaster,
     * Time Frame in which the Disaster Occurred.
     *
     *
     * Initially at the Start of the Activity A Map is shown to the User, where the User can pick a Location.
     * At this Location the Disaster is reported.
     *
     * The Disaster is Saved in the Firebase FireStore.
     * */

    Button b1,sb;
    Spinner sd,ss,st,spi;
    TextView t1;
    int PLACE_PICKER_REQUEST=1;
    String s1pi,s2d,s3s,s4t,t90,s5lat="",s6lon="",t09,t10;
    int pi_con;

    //Day2
    DBReport d3;
    int map_style_file;
    TextView HReport_Disaster;
    TextView SHUpcoming_Previous,SHClick_Button,SHSelect_Disaster,SHSelect_Severity,SHSelect_Time;

    //Day 3
    float a2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        getSupportActionBar().setTitle("Report Disasters");


        HReport_Disaster = findViewById(R.id.tv1);
        SHUpcoming_Previous = findViewById(R.id.lab_for_PI);
        SHClick_Button = findViewById(R.id.lab_for_bt_pick);
        SHSelect_Disaster = findViewById(R.id.lab_for_sD);
        SHSelect_Severity=findViewById(R.id.lab_for_sS);
        SHSelect_Time = findViewById(R.id.lab_for_sT);


        b1=findViewById(R.id.bt_picker);
        t1=findViewById(R.id.text_show);
        sb=findViewById(R.id.sub_But);

        b1.setVisibility(View.GONE);
        SHClick_Button.setVisibility(View.GONE);
//Button Made Invisible
        sd=findViewById(R.id.spinnerD);
        ss=findViewById(R.id.spinnerS);
        st=findViewById(R.id.spinnerT);
        spi=findViewById(R.id.spinnerPI);




        TextView[] ta = {HReport_Disaster,
                SHUpcoming_Previous,
                SHClick_Button,
                SHSelect_Disaster,
                SHSelect_Severity,
                SHSelect_Time,t1};

        Spinner[] s_array = {st,ss,spi,sd};

        ConstraintLayout constraintLayout = findViewById(R.id.activity_user_form_file);


        SharedPreferences sh = getSharedPreferences("UP", MODE_PRIVATE);
        a2 = (float)sh.getInt("size", 17);
        map_style_file =sh.getInt("color",0);
        new Helpers11().setColorB(constraintLayout,map_style_file,ta);

        subSetTextSize(ta,a2);

        ArrayAdapter adapter1 = getArrayAdapter(R.array.spinner_items_disaster_type);
        sd.setAdapter(adapter1);
        adapter1 = getArrayAdapter(R.array.spinner_items_severity_type);
        ss.setAdapter(adapter1);
        adapter1 = getArrayAdapter(R.array.spinner_items_prev_inc_type);
        spi.setAdapter(adapter1);

        Helpers11 h = new Helpers11();
        Intent intent = h.getMap(ReportDisasterActivity.this,map_style_file);
        startActivityForResult(intent, 1);
        h.setButtonBF(sb,map_style_file);
        sb.setTextSize(a2);



        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    st.setAdapter(getArrayAdapter(R.array.spinner_items_l_time_type)
                    );
                }
                else{
                    st.setAdapter(getArrayAdapter(R.array.spinner_items_n_time_type)
                    );
                }
                st.setVisibility(View.VISIBLE);
                findViewById(R.id.lab_for_sT).setVisibility(View.VISIBLE);

                t90=adapterView.getItemAtPosition(i).toString();
                s1pi=t90;
                pi_con=i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                t90=adapterView.getItemAtPosition(i).toString();
                s4t=t90;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                t90=adapterView.getItemAtPosition(i).toString();
                s2d=t90;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                t90=adapterView.getItemAtPosition(i).toString();
                s3s =t90;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers11 h = new Helpers11();
                Intent intent = h.getMap(ReportDisasterActivity.this,map_style_file);
                startActivityForResult(intent, 1);
            }
        });
*/
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(s5lat.isEmpty() && s6lon.isEmpty()){
                    Toast.makeText(ReportDisasterActivity.this, "Kindly Select Location", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    AlertDialog alertDialog = new AlertDialog.Builder(ReportDisasterActivity.this)
                            .setTitle("Confirmation").setMessage("Are you Sure to Report this")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Date c1 = Calendar.getInstance().getTime();

                                    db.collection("all_reports").add(new Report1(s1pi, s2d, s3s, s4t, s5lat, s6lon, String.valueOf(c1)))
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(ReportDisasterActivity.this, "Reported Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ReportDisasterActivity.this, "An Error Occurred , Try again Later", Toast.LENGTH_SHORT).show();
                                                }
                                            });

//                                    DecimalFormat df = new DecimalFormat("#.#");
//                                    String t09= df.format(Double.parseDouble(s5lat));
//                                    String t10= df.format(Double.parseDouble(s6lon));
                                    db.collection("reports_loc").document("A").collection(t09+"_"+t10).add(new Report1(s1pi, s2d, s3s, s4t, s5lat, s6lon, String.valueOf(c1)))
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(ReportDisasterActivity.this, "Reported Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ReportDisasterActivity.this, "An Error Occurred , Try again Later", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);

                String lat,lon,address;
                lat=String.valueOf(addressData.getLatitude());
                s5lat+=lat;
                t09=String.valueOf((int)addressData.getLatitude());
                lon=String.valueOf(addressData.getLongitude());
                s6lon+=lon;
                t10=String.valueOf((int)addressData.getLongitude());
                address= String.valueOf(addressData.component3().get(0).getAddressLine(0));


                t1.setVisibility(View.VISIBLE);
                String stringBuilder = "Selected Location: \n LATITUDE : "+lat+"\n LONGITUDE : "+lon;
                stringBuilder+="\n Address : "+address;

                t1.setText(stringBuilder);

//                Toast.makeText(this, addressData.component3()+"\n", Toast.LENGTH_SHORT).show();
            }else if(resultCode== Activity.RESULT_CANCELED || data == null){
                finish();

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void subSetTextSize(TextView[] ta,float size){

        ta[0].setTextSize(size+2);
        for(int i=1;i<ta.length-1;i++){
            ta[i].setTextSize(size);

        }
        ta[ta.length-1].setTextSize(size-1);
       /* for (TextView t1:ta) {
            t1.setTextSize(size);
        }*/

    }

    private ArrayAdapter getArrayAdapter(int res_id){

        return new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(res_id)){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ReportDisasterActivity.this);
                    convertView = inflater.inflate(
                            android.R.layout.simple_spinner_item, parent, false);
                }

                TextView tv = (TextView) convertView
                        .findViewById(android.R.id.text1);
                tv.setSingleLine();
                tv.setEllipsize(TextUtils.TruncateAt.END);
                tv.setText(getResources().getStringArray(res_id)[position]);
                tv.setTextSize(a2);
                return convertView;

            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = ((TextView) v);
                tv.setTextColor(Color.parseColor(new Helpers11().getSpinBack(map_style_file)));
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                tv.setSingleLine();
                tv.setEllipsize(TextUtils.TruncateAt.END);
                tv.setTextSize(a2);
                return v;
            }

        };
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
                startActivity(new Intent(ReportDisasterActivity.this, AboutAppActivity.class));
                return(true);
/*            case R.id.exit_app:
                finish();
                System.exit(0);
                return(true);*/
            case R.id.sign_out:
                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ReportDisasterActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    SharedPreferences sharedPreferences12 = getSharedPreferences("UP1", MODE_PRIVATE);
                    SharedPreferences.OnSharedPreferenceChangeListener listener3 = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences12, String s) {
                            startActivity(new Intent(ReportDisasterActivity.this, MainActivity.class));
                            finish();
                        }
                    };
                    sharedPreferences12.registerOnSharedPreferenceChangeListener(listener3);
                    SharedPreferences.Editor myEdit3 = sharedPreferences12.edit();
                    myEdit3.putBoolean("cup_login", false).apply();
                }
                return (true);
            case R.id.reported_eve:
                startActivity(new Intent(ReportDisasterActivity.this,View_DEve.class));
                return (true);
           /* case R.id.settings_user:
                startActivity(new Intent(ReportDisasterActivity.this,AboutAppActivity.class)); */
        }
        return(super.onOptionsItemSelected(item));
    }

}




