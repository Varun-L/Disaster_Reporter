package com.icy.dmi;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    /**
     * This is also one of the Key Activities where the User has two choices to login.
     * He can login with registered credentials or CUP Login Credentials.
     * Accordingly at the Login Itself we will store his Color Preferences using SharedPreferences,
     * which avoids unwanted reads from the Database.
     * Once if we read the Data from the User DB we can store in the SharedPreference and access them whenever necessary.
     * We store size and color chosen by user in each case(CUP Web Email Login / Email based Login).
     * */


    Button eL,eC;
    SharedPreferences sharedPreferences,sharedPreferences1;
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if(s.equals("color")){
                startActivity(new Intent(SignInActivity.this, MainActivity2.class));
                finish();
            }
        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        eL = findViewById(R.id.emailSignInButton);
        eC=findViewById(R.id.emailSignInCUPButton);

        getSupportActionBar().setTitle("Login ");

        sharedPreferences = getSharedPreferences("UP",MODE_PRIVATE);
        sharedPreferences1 = getSharedPreferences("UP1",MODE_PRIVATE);

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        eL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText emE, epE;
                String em,ep;
                emE=(EditText)findViewById(R.id.fieldEmail);
                epE=(EditText) findViewById(R.id.fieldPassword);
                em = emE.getText().toString();
                ep=  epE.getText().toString();
                if(!(em.length()>0 && ep.length()>0)){
                    Toast.makeText(SignInActivity.this, "Email and Password cannot be Empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(em, ep)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        setUTSize();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignInActivity.this, "Authentication failed. Try Again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }); }
                }
        });
        eC.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {


                EditText emE, epE;
                String em,ep;
                emE=(EditText)findViewById(R.id.fieldEmail);
                epE=(EditText) findViewById(R.id.fieldPassword);
                em = emE.getText().toString();
                ep=  epE.getText().toString();
                if(!(em.length()>0 && ep.length()>0)){
                    Toast.makeText(SignInActivity.this, "Email and Password cannot be Empty.", Toast.LENGTH_SHORT).show();
                }
                else {

                    setUTSize1(em,ep);

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPreferences("UP",MODE_PRIVATE).registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSharedPreferences("UP",MODE_PRIVATE).registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSharedPreferences("UP",MODE_PRIVATE).registerOnSharedPreferenceChangeListener(listener);

    }

    public void setUTSize(){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String UId=FirebaseAuth.getInstance().getUid();

        mDatabase.child("Users").child(UId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Toast.makeText(SignInActivity.this, "Error getting data", Toast.LENGTH_SHORT).show();
                }
                else {
                    int t11,t12;
                    DBReport d3= task.getResult().getValue(DBReport.class);
                    switch (d3.UTSize){
                        case 0: t11=14; break;
                        case 1: t11=17; break;
                        case 2: t11=20; break;
                        case 3: t11=23; break;
                        default: t11=18;
                    }
                    t12=d3.UColor;
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putInt("size",t11).apply();
                    myEdit.putInt("color",t12).apply();


                }
            }});




    }

    public void setUTSize1(String un, String pwd){

                    String[] x ={"",""};

                   new MainActivity45().postDataUsingVolley(new VolleyCallBack() {
                       @Override
                       public void onSuccessResponse(String[] result) {
                           x[0]=result[0];
                           x[1]=result[1];
                           if(x[0].equals("-9999")){
                               Toast.makeText(SignInActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               int t11=18,t12=0;
                               String ts1;

                               t11=Integer.parseInt(x[0]);
                               ts1=x[1];
                               switch(ts1){
                                   case "Any":t12=0; break;
                                   case "Black_White":t12=6; break;
                                   case "Blue_Yellow":t12=2; break;

                               }
                               SharedPreferences.Editor myEdit = sharedPreferences.edit();
                               SharedPreferences.Editor myEdit1 = sharedPreferences1.edit();
                               myEdit.putInt("size",t11).apply();
                               myEdit.putInt("color",t12).apply();
                               myEdit1.putBoolean("cup_login",true).apply();


                           }
                       }
                   }, un, pwd, SignInActivity.this);

/*                   if(x[0].equals("-9999")){
                        Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        t11=Integer.parseInt(x[0]);
                        ts1=x[1];
                        switch(ts1){
                            case "Any":t12=0; break;
                            case "Black_White":t12=6; break;
                            case "Blue_Yellow":t12=1; break;

                        }

                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putInt("size",t11).apply();
                        myEdit.putInt("color",t12).apply();


                    }
*/
    }



}


