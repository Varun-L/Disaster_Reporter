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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;



public class GMaps1 extends AppCompatActivity {


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
        float a2 = (float)sh.getInt("size", 17);
        map_style_file =sh.getInt("color",0);
        new Helpers11().setColorB(constraintLayout,map_style_file,ta);
        subSetTextSize(ta,a2);

        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    st.setAdapter(new ArrayAdapter<String>(GMaps1.this, android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.spinner_items_l_time_type)));
                }
                else{
                    st.setAdapter(new ArrayAdapter<String>(GMaps1.this, android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.spinner_items_n_time_type)));
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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers11 h = new Helpers11();
                Intent intent = h.getMap(GMaps1.this,map_style_file);
                startActivityForResult(intent, 1);
            }
            });

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(s5lat.isEmpty() && s6lon.isEmpty()){
                    Toast.makeText(GMaps1.this, "Kindly Select Location", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    AlertDialog alertDialog = new AlertDialog.Builder(GMaps1.this)
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
                                                    Toast.makeText(GMaps1.this, "Reported Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(GMaps1.this, "An Error Occurred , Try again Later", Toast.LENGTH_SHORT).show();
                                                }
                                            });

//                                    DecimalFormat df = new DecimalFormat("#.#");
//                                    String t09= df.format(Double.parseDouble(s5lat));
//                                    String t10= df.format(Double.parseDouble(s6lon));
                                    db.collection("reports_loc").document("A").collection(t09+"_"+t10).add(new Report1(s1pi, s2d, s3s, s4t, s5lat, s6lon, String.valueOf(c1)))
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(GMaps1.this, "Reported Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(GMaps1.this, "An Error Occurred , Try again Later", Toast.LENGTH_SHORT).show();
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
            }
        } else {
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
                startActivity(new Intent(GMaps1.this,SettingsActivity.class));
                return(true);
/*            case R.id.exit_app:
                finish();
                System.exit(0);
                return(true);*/
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(GMaps1.this,MainActivity.class));
                finish();
                return (true);
            case R.id.reported_eve:
                startActivity(new Intent(GMaps1.this,View_DEve.class));
                return (true);
           /* case R.id.settings_user:
                startActivity(new Intent(GMaps1.this,SettingsActivity.class)); */
        }
        return(super.onOptionsItemSelected(item));
    }

}




   /* public Intent getMap(){
        if (map_style_file==0)
         return new com.sucho.placepicker.PlacePicker.IntentBuilder()
                .setLatLong(12.9716, 77.5946)  // Initial Latitude and Longitude the Map will load into
                .showLatLong(true)  // Show Coordinates in the Activity
                .setMapZoom(14.0f)  // Map Zoom Level. Default: 14.0
                .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerDrawable(R.drawable.s13) // Change the default Marker Image
//                        .setMarkerImageImageColor(R.color.colorPrimary)
                .setFabColor(R.color.quantum_purple)
                .setPrimaryTextColor(R.color.colorPrimary) // Change text color of Shortened Address
                .setSecondaryTextColor(R.color.colorAccent) // Change text color of full Address
                .setBottomViewColor(R.color.design_default_color_background) // Change Address View Background Color (Default: White)
                .setMapRawResourceStyle(R.raw.mapstylestandard)  //Set Map Style (https://mapstyle.withgoogle.com/)
                .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                .build(GMaps1.this);
        if(map_style_file==1)
           return new com.sucho.placepicker.PlacePicker.IntentBuilder()
                    .setLatLong(12.9716, 77.5946)  // Initial Latitude and Longitude the Map will load into
                    .showLatLong(true)  // Show Coordinates in the Activity
                    .setMapZoom(14.0f)  // Map Zoom Level. Default: 14.0
                    .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                    .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerDrawable(R.drawable.s13) // Change the default Marker Image
//                        .setMarkerImageImageColor(R.color.colorPrimary)
                    .setFabColor(R.color.quantum_purple)
                    .setPrimaryTextColor(R.color.colorPrimary) // Change text color of Shortened Address
                    .setSecondaryTextColor(R.color.colorAccent) // Change text color of full Address
                    .setBottomViewColor(R.color.design_default_color_background) // Change Address View Background Color (Default: White)
                    .setMapRawResourceStyle(R.raw.mapstyleaubergine)  //Set Map Style (https://mapstyle.withgoogle.com/)
                    .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                    .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                    .build(GMaps1.this);
            if(map_style_file==2)
               return new com.sucho.placepicker.PlacePicker.IntentBuilder()
                        .setLatLong(12.9716, 77.5946)  // Initial Latitude and Longitude the Map will load into
                        .showLatLong(true)  // Show Coordinates in the Activity
                        .setMapZoom(14.0f)  // Map Zoom Level. Default: 14.0
                        .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                        .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerDrawable(R.drawable.s13) // Change the default Marker Image
//                        .setMarkerImageImageColor(R.color.colorPrimary)
                        .setFabColor(R.color.quantum_purple)
                        .setPrimaryTextColor(R.color.colorPrimary) // Change text color of Shortened Address
                        .setSecondaryTextColor(R.color.colorAccent) // Change text color of full Address
                        .setBottomViewColor(R.color.design_default_color_background) // Change Address View Background Color (Default: White)
                        .setMapRawResourceStyle(R.raw.mapstyledark)  //Set Map Style (https://mapstyle.withgoogle.com/)
                        .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                        .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                        .build(GMaps1.this);
            if(map_style_file==3)
               return new com.sucho.placepicker.PlacePicker.IntentBuilder()
                        .setLatLong(12.9716, 77.5946)  // Initial Latitude and Longitude the Map will load into
                        .showLatLong(true)  // Show Coordinates in the Activity
                        .setMapZoom(14.0f)  // Map Zoom Level. Default: 14.0
                        .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                        .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerDrawable(R.drawable.s13) // Change the default Marker Image
//                        .setMarkerImageImageColor(R.color.colorPrimary)
                        .setFabColor(R.color.quantum_purple)
                        .setPrimaryTextColor(R.color.colorPrimary) // Change text color of Shortened Address
                        .setSecondaryTextColor(R.color.colorAccent) // Change text color of full Address
                        .setBottomViewColor(R.color.design_default_color_background) // Change Address View Background Color (Default: White)
                        .setMapRawResourceStyle(R.raw.mapstyleretro)  //Set Map Style (https://mapstyle.withgoogle.com/)
                        .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                        .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                        .build(GMaps1.this);
            else
                return new com.sucho.placepicker.PlacePicker.IntentBuilder()
                        .setLatLong(12.9716, 77.5946)  // Initial Latitude and Longitude the Map will load into
                        .showLatLong(true)  // Show Coordinates in the Activity
                        .setMapZoom(14.0f)  // Map Zoom Level. Default: 14.0
                        .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                        .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
//                        .setMarkerDrawable(R.drawable.s13) // Change the default Marker Image
//                        .setMarkerImageImageColor(R.color.colorPrimary)
                        .setFabColor(R.color.quantum_purple)
                        .setPrimaryTextColor(R.color.colorPrimary) // Change text color of Shortened Address
                        .setSecondaryTextColor(R.color.colorAccent) // Change text color of full Address
                        .setBottomViewColor(R.color.design_default_color_background) // Change Address View Background Color (Default: White)
                        .setMapRawResourceStyle(R.raw.mapstylesilver)  //Set Map Style (https://mapstyle.withgoogle.com/)
                        .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                        .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                        .build(GMaps1.this);
    }
*/
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address1 = String.valueOf(place.getAddress());
                s5lat=latitude;
                s6lon=longitude;

                stringBuilder.append("Selected Location: \n");
                stringBuilder.append("LATITUDE :");
                stringBuilder.append(latitude);
                stringBuilder.append("\nLONGITUDE :");
                stringBuilder.append(longitude);
                if (address1.length()>1) {
                    stringBuilder.append("Address :");
                    stringBuilder.append(address1);
                }

                t1.setVisibility(View.VISIBLE);
                t1.setText(stringBuilder.toString());

            }
        }
    }
      */
/*
    public void setTextSize(TextView[] ta){
         DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String UId=FirebaseAuth.getInstance().getUid();

        mDatabase.child("Users").child(UId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Toast.makeText(GMaps1.this, "Error getting data", Toast.LENGTH_SHORT).show();
                }
                else {

                    d3= task.getResult().getValue(DBReport.class);
                    Toast.makeText(GMaps1.this, task.getResult().getValue().toString(), Toast.LENGTH_SHORT).show();
                    switch (d3.UTSize){
                        case 0: subSetTextSize(ta,14); break;
                        case 1: subSetTextSize(ta,17); break;
                        case 2: subSetTextSize(ta,20); break;
                        case 3: subSetTextSize(ta,23); break;
                        default: subSetTextSize(ta,17);
                    }
                    map_style_file=d3.UColor;

                }
            }
        });

    }
*/
