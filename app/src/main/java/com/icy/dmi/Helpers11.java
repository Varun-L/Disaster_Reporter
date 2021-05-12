package com.icy.dmi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sucho.placepicker.MapType;

import java.io.StringReader;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Helpers11 {

    /**
     * Helpers is a Helper Class which consists of functions that are used in various activities.
     *
     * setColorB function takes an TextView array and sets their text size, background color.
     *
     * setColorBF function sets the Background and Foreground Color of Buttons.
     *
     * getSpinBack gives the Background Color for the spinner to be set.
     *
     * getMap function returns the Map based on ColorContrast that the user has chosen.
     * */

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

    public void setButtonBF1(Button v, int map_style_file){
        switch (map_style_file){
            case 0: v.setBackgroundColor(Color.parseColor("#FFFFFF"));
                v.setTextColor(Color.parseColor("#000000"));
//                      White
                break;
            case 1: v.setBackgroundColor(Color.parseColor("#6666ff"));
                v.setTextColor(Color.parseColor("#FFFFFF"));

//                    Blue
                break;

            case 2: v.setBackgroundColor(Color.parseColor("#ffff80"));
                v.setTextColor(Color.parseColor("#000000"));

//                Yellow
                break;
            case 3: v.setBackgroundColor(Color.parseColor("#cccccc"));
                v.setTextColor(Color.parseColor("#000000"));

//                Silver
                break;

            case 4: v.setBackgroundColor(Color.parseColor("#cc66ff"));
                v.setTextColor(Color.parseColor("#000000"));

//                Purple
                break;

            case 5: v.setBackgroundColor(Color.parseColor("#66ff99"));
                v.setTextColor(Color.parseColor("#000000"));

//                Green
                break;
            case 6: v.setBackgroundColor(Color.parseColor("#42423d"));
                v.setTextColor(Color.parseColor("#FFFFFF"));

//Night
                break;
            case 7: v.setBackgroundColor(Color.parseColor("#42423d"));
                v.setTextColor(Color.parseColor("#FFFFFF"));

//Dark
                break;
            default: v.setBackgroundColor(Color.parseColor("#FF0000")); break;

        }
    }
    public void setButtonBF(Button v, int map_style_file){
        switch (map_style_file){
            case 0: v.setTextColor(Color.parseColor("#FFFFFF"));
                v.setBackgroundColor(Color.parseColor("#000000"));
//                      White
                break;
            case 1: v.setTextColor(Color.parseColor("#6666ff"));
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));

//                    Blue
                break;

            case 2: v.setTextColor(Color.parseColor("#ffff80"));
                v.setBackgroundColor(Color.parseColor("#000000"));

//                Yellow
                break;
            case 3: v.setTextColor(Color.parseColor("#cccccc"));
                v.setBackgroundColor(Color.parseColor("#000000"));

//                Silver
                break;

            case 4: v.setTextColor(Color.parseColor("#cc66ff"));
                v.setBackgroundColor(Color.parseColor("#000000"));

//                Purple
                break;

            case 5: v.setTextColor(Color.parseColor("#66ff99"));
                v.setBackgroundColor(Color.parseColor("#000000"));

//                Green
                break;
            case 6: v.setTextColor(Color.parseColor("#42423d"));
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));

//Night
                break;
            case 7: v.setTextColor(Color.parseColor("#42423d"));
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));

//Dark
                break;
            default: v.setTextColor(Color.parseColor("#FF0000")); break;

        }
    }


    public String getSpinBack(int map_style_file){
        switch (map_style_file){
            case 0:
                return("#000000");
//                      White
            case 1:
                return("#FFFFFF");
//                    Blue
            case 2:
                return("#000000");
//                Yellow
            case 3:
                return("#000000");
//                Silver
            case 4:
                return("#000000");
//                Purple
            case 5: return("#000000");
//                Green
            case 6: return("#FFFFFF");
//Night
            case 7: return("#FFFFFF");
//Dark
            default: return("#FFFFFF");
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

/*

                        content = new StringBuilder();
                        while ((line = br.readLine()) != null) {
                            if(t1==4 || t1==5){
                                content.append(line);
                            }
                            t1++;
                        }
                    }

        line=content.toString();
        if(line.isEmpty()){
            return new String[]{};
        }else{
            return k99(content.toString());
        }


    }*/

    public String[] k99(String a2){
        String a=a2;
        a=a.replace("&lt;","<").replace("&gt;",">");
        a=a.replace("<FontSize>","").replace("<ColourContrast>","");
        a=a.replace("</ColourContrast>","").replace("</FontSize>","@").trim();
        String[] ar = a.split("@", 2);
        return ar;
    }



}


class MainActivity45 extends AppCompatActivity {


    /**
     * MainActivity45 is used mainly for enabling CUP Login.
     *  It uses Volley to sendRequest which has username and password and gets the User Selected TextSize and Color
     *  from the website.
     *
     * */
    public void postDataUsingVolley(VolleyCallBack callBack, String un, String pwd, Activity a) {
        // url to post our data
        String url = "http://cambum.net/UserConfigService.asmx/LogInFormSubmit";
        RequestQueue queue = Volley.newRequestQueue(a);
        String[] res={"",""};
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                        response=response.replace("&lt;","<").replace("&gt;",">");
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(new InputSource(new StringReader(response)));
                        if(doc.getDocumentElement().getTextContent().equals("User Not Found")){
                            res[0]="-9999";
                        }

                        else {
                            String a4 = (doc.getElementsByTagName("FontSize").item(0).getTextContent().trim());
                            String a2 = doc.getElementsByTagName("ColourContrast").item(0).getTextContent().trim();
                            res[0]=a4;
                            res[1]=a2;
                        }
                    callBack.onSuccessResponse(res);

                } catch ( Exception e) {
                    Toast.makeText(a, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(a, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("UserName", un);
                params.put("Password", pwd);
                params.put("DeviceType","1");
                params.put("ScreenX","1536");
                params.put("ScreenY","864");
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }
}

interface VolleyCallBack{

    void onSuccessResponse(String[] result);
}

