package com.icy.dmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button eR;
    Spinner spE1,spE2;
    EditText emE,epE1,epE2;
    String em,ep1,ep2;
    int sp1,sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);
        eR = findViewById(R.id.emailCreateAccountButton);
        getSupportActionBar().setTitle("Register");

         DatabaseReference mDatabase;
         mDatabase = FirebaseDatabase.getInstance().getReference();


         spE1=findViewById(R.id.spinner_textSizeSelectApp);
        spE2=findViewById(R.id.spinner_color_map);
        emE=(EditText)findViewById(R.id.fieldEmail);
        epE1=(EditText) findViewById(R.id.fieldPassword1);
        epE2=(EditText) findViewById(R.id.fieldPassword2);



        spE1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp1=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spE2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp2=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        eR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                em = emE.getText().toString();
                ep1= epE1.getText().toString();
                ep2= epE2.getText().toString();
                if(!(ep1.trim().equals(ep2.trim()))){
                    Toast.makeText(RegisterActivity.this, "Password and Confirm Password Does not match", Toast.LENGTH_SHORT).show();
                }else{


                    mAuth.createUserWithEmailAndPassword(em, ep1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(RegisterActivity.this, "Authentication Successful. User Registered", Toast.LENGTH_SHORT).show();
                                        FirebaseUser firebaseUser=mAuth.getCurrentUser();
                                        firebaseUser.getEmail();
                                        String UserId = firebaseUser.getUid();

                                        SharedPreferences sharedPreferences = getSharedPreferences("UP",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        int t11,t12;
                                        switch (sp1){
                                            case 0: t11=14; break;
                                            case 1: t11=17; break;
                                            case 2: t11=20; break;
                                            case 3: t11=23; break;
                                            default: t11=18;
                                        }
                                        t12=sp2;
                                        myEdit.putInt("size",t11).commit();
                                        myEdit.putInt("color",t12).commit();
                                        myEdit.apply();


                                        DBReport d1= new DBReport(UserId,sp1,sp2);
//                                        DBReport d2= new DBReport(UserId,sp1,sp2,firebaseUser.getEmail());
                                        mDatabase.child("Users").child(firebaseUser.getUid()).setValue(d1).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        mDatabase.child("UserDet").child(firebaseUser.getUid()).setValue(d2)
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "Something Went Wrong , Try Again Later", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        startActivity(new Intent(RegisterActivity.this,GMaps1.class));
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }



            }
        });



    }
}