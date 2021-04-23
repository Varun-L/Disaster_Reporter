package com.icy.dmi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.sucho.placepicker.MapType;

public class Helpers11 {


    public void setColorB(ViewGroup v, int map_style_file, TextView[] ta){
        switch (map_style_file){
            case 0: v.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    for (TextView t1:ta) {
                        t1.setTextColor(Color.parseColor("#000000"));
                    }
//                    White
                break;
            case 1: v.setBackgroundColor(Color.parseColor("#6666ff"));
                    for (TextView t1:ta) {
                        t1.setTextColor(Color.parseColor("#FFFFFF"));
                    }
//                    Blue
            break;

            case 2: v.setBackgroundColor(Color.parseColor("#ffff80"));
                for (TextView t1:ta) {
                    t1.setTextColor(Color.parseColor("#000000"));
                }
//                Yellow
                break;
            case 3: v.setBackgroundColor(Color.parseColor("#cccccc"));
                for (TextView t1:ta) {
                    t1.setTextColor(Color.parseColor("#000000"));
                }
//                Silver
                break;

            case 4: v.setBackgroundColor(Color.parseColor("#cc66ff"));
                for (TextView t1:ta) {
                    t1.setTextColor(Color.parseColor("#000000"));
                }
//                Purple
                break;

            case 5: v.setBackgroundColor(Color.parseColor("#66ff99"));
                for (TextView t1:ta) {
                    t1.setTextColor(Color.parseColor("#000000"));
                }
//                Green
                break;
            case 6: v.setBackgroundColor(Color.parseColor("#42423d"));
                    for (TextView t1:ta) {
                        t1.setTextColor(Color.parseColor("#FFFFFF"));
                    }
//Night
                break;
            case 7: v.setBackgroundColor(Color.parseColor("#42423d"));
                for (TextView t1:ta) {
                    t1.setTextColor(Color.parseColor("#FFFFFF"));
                }
//Dark
                break;
            default: v.setBackgroundColor(Color.parseColor("#FF0000")); break;

        }
    }


    /*   White -0
         Blue -1
         Yellow -2
         Silver -3
         Purple -4
         Green -5
         Black(Night Mode)-6
         Black(Dark Mode) -7 */
    public Intent getMap(Activity a,int map_style_file){
        if (map_style_file==0){
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
                    .build(a);}
        else if(map_style_file==1) {
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
                    .build(a);}
        else if(map_style_file==2){
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
                    .build(a);}
        else if(map_style_file==3){
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
                    .build(a);}
        else if(map_style_file==4){
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
                    .setMapRawResourceStyle(R.raw.mapstylepurple)  //Set Map Style (https://mapstyle.withgoogle.com/)
                    .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                    .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                    .build(a);
        }
        else if(map_style_file==5){
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
                    .setMapRawResourceStyle(R.raw.mapstylegreen)  //Set Map Style (https://mapstyle.withgoogle.com/)
                    .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                    .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                    .build(a);
        }
        else if(map_style_file==7){
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
                    .build(a);}
        else{
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
                    .setMapRawResourceStyle(R.raw.mapstylenight)  //Set Map Style (https://mapstyle.withgoogle.com/)
                    .setMapType(MapType.NORMAL)
//                        .setPlaceSearchBar(false,"") //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
//                        .onlyCoordinates(true)  //Get only Coordinates from Place Picker
                    .hideLocationButton(true)   //Hide Location Button (Default: false)
//                        .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                    .build(a);
        }
    }




}
