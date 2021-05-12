package com.icy.dmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /**
     * MainActivity has Two Buttons one for Sign In and the Other for Sign Up.
     * If User Selects Sign In he will be redirected to SignInActivity
     * else to RegisterActivity
     *
     * If the User is already logged in he will be redirected to MainActivity2 where he can Report Disasters and View Existing Reported Ones.
     * */

    Button eSB,eCB;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        eSB = findViewById(R.id.emailSignInButton);
        eCB = findViewById(R.id.emailCreateAccountButton);

        eSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , SignInActivity.class);
                startActivity(i);

            }
        });

        eCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , RegisterActivity.class);
                startActivity(i);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
            finish();
//            Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences sharedPreferences1 = getSharedPreferences("UP1",MODE_PRIVATE);
        if(sharedPreferences1.getBoolean("cup_login", false)){
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
            finish();
        }

    }


}


