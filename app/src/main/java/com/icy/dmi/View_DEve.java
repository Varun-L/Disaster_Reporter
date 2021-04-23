package com.icy.dmi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;

import java.text.DecimalFormat;
import java.util.Map;

public class View_DEve extends AppCompatActivity {

    Button b1;
    TextView t1,tlab2;
    int map_style_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__d_eve);
    b1=findViewById(R.id.bt_picker_view);
    t1=findViewById(R.id.show_dis_events);
    tlab2=findViewById(R.id.lab_for_bt_picker_view);
    getSupportActionBar().setTitle("View Reported Disaster");



        TextView[] ta = {t1,tlab2};

    RelativeLayout r1 = findViewById(R.id.activity_view_d_events);
    SharedPreferences sh = getSharedPreferences("UP", MODE_PRIVATE);
    float a2 = (float)sh.getInt("size", 17);
    map_style_file =sh.getInt("color",0);
    subSetTextSize(ta,a2);
    new Helpers11().setColorB(r1,map_style_file,ta);


        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Helpers11 h = new Helpers11();
            Intent intent = h.getMap(View_DEve.this,map_style_file);
            startActivityForResult(intent, 1);

        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);
                String la,lo,address1;
                la=String.valueOf((int)addressData.getLatitude());
                lo=String.valueOf((int)addressData.getLongitude());
//                la=String.valueOf(addressData.getLatitude());
//                lo=String.valueOf(addressData.getLongitude());
                address1= String.valueOf(addressData.component3().get(0).getAddressLine(0));
//                DecimalFormat df = new DecimalFormat("#.#");
//                String t09= df.format(Double.parseDouble(la));
//                String t10= df.format(Double.parseDouble(lo));
                String t09,t10;
                t09=la;
                t10=lo;
                String string="";
                t1.setText(string);
                Toast.makeText(this, t09+"_"+t10, Toast.LENGTH_SHORT).show();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("reports_loc").document("A").collection(t09+"_"+t10)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int k2=1;
                                    String string1= "";
                                    string1+="Selected Location: \n LATITUDE : "+la+"\n LONGITUDE : "+lo+"\n ADDRESS : "+address1;
                                    string1+="\n\tDisasters Reported :\n";
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        string1+="\n"+String.valueOf(k2)+" => \n";
                                        Map<String, Object> m =document.getData();

                                        for (String s: m.keySet()) {
                                            string1+=(s+"->"+m.get(s)+"\n");
                                        }
                                        k2++;
                                        t1.setText(string1);

                                    }
                                } else {
                                    Toast.makeText(View_DEve.this, "Error getting documents.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                if(t1.getText().toString().equals("")){
                    t1.setText("No Disasters Reported at the Selected Location");
                }


            }
        }
    }

    public void subSetTextSize(TextView[] ta,float size){

        ta[0].setTextSize(size-2);
        for(int i=1;i<ta.length;i++){
            ta[i].setTextSize(size);
        }
//        ta[ta.length-1].setTextSize(size-1);
       /* for (TextView t1:ta) {
            t1.setTextSize(size);
        }*/

    }



}








/*
  PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(View_DEve.this),1);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
 */


/*    public Intent getMap(){
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
                    .build(View_DEve.this);
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
                    .build(View_DEve.this);
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
                    .build(View_DEve.this);
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
                    .build(View_DEve.this);
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
                    .build(View_DEve.this);
    }
*/