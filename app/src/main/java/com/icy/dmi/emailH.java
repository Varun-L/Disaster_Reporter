package com.icy.dmi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class emailH extends AppCompatActivity {

    Button eL;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if(s.equals("color")){
                startActivity(new Intent(emailH.this, MainActivity2.class));
                finish();}
        }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        eL = findViewById(R.id.emailSignInButton);
        getSupportActionBar().setTitle("Login ");

        sharedPreferences = getSharedPreferences("UP",MODE_PRIVATE);
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
                    Toast.makeText(emailH.this, "Email and Password cannot be Empty.", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(emailH.this, "Authentication failed. Try Again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }); }
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

    public void setUTSize(){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String UId=FirebaseAuth.getInstance().getUid();

        mDatabase.child("Users").child(UId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                    Toast.makeText(emailH.this, "Error getting data", Toast.LENGTH_SHORT).show();
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





}


