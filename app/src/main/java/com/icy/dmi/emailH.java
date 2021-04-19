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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        eL = findViewById(R.id.emailSignInButton);
        getSupportActionBar().setTitle("Login ");

        SharedPreferences sharedPreferences = getSharedPreferences("UP",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();


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
                ep=epE.getText().toString();

                mAuth.signInWithEmailAndPassword(em, ep)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    setUTSize(myEdit);
                                    Toast.makeText(emailH.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                                    sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                                        @Override
                                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                                            startActivity(new Intent(emailH.this,GMaps1.class));
                                            finish();
                                        }
                                    });


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(emailH.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





                }
        });

    }

    public void setUTSize(SharedPreferences.Editor myEdit){

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
                    Toast.makeText(emailH.this, task.getResult().getValue().toString(), Toast.LENGTH_SHORT).show();
                    switch (d3.UTSize){
                        case 0: t11=14; break;
                        case 1: t11=17; break;
                        case 2: t11=20; break;
                        case 3: t11=23; break;
                        default: t11=18;
                    }
                    t12=d3.UColor;
                    myEdit.putInt("size",t11).commit();
                    myEdit.putInt("color",t12).commit();
                    myEdit.apply();


                }
            }});



    }



}


