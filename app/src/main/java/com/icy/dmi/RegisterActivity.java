package com.icy.dmi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Collections;

public class RegisterActivity extends AppCompatActivity {

    /**
     * RegisterActivity Registers a User to the Firebase.
     * We will take the User Email, Password, His Font Size Choice , Color Contrast Choice.
     * */

    Button eR;
    Spinner spE1;
    EditText emE,epE1,epE2;
    String em,ep1,ep2;
    int sp1,sp2;
    TextView t99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        eR = findViewById(R.id.emailCreateAccountButton);
        getSupportActionBar().setTitle("Register");

         DatabaseReference mDatabase;
         mDatabase = FirebaseDatabase.getInstance().getReference();


         spE1=findViewById(R.id.spinner_textSizeSelectApp);
//        spE2=findViewById(R.id.spinner_color_map);
        emE=(EditText)findViewById(R.id.fieldEmail);
        epE1=(EditText) findViewById(R.id.fieldPassword1);
        epE2=(EditText) findViewById(R.id.fieldPassword2);
        t99 = findViewById(R.id.demo_text);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.spinner_choose_text_size, R.layout.custom_text_view_1);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_choose_text_size)){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RegisterActivity.this);
                    convertView = inflater.inflate(
                            android.R.layout.simple_spinner_item, parent, false);
                }

                TextView tv = (TextView) convertView
                        .findViewById(android.R.id.text1);
                tv.setSingleLine();
                tv.setEllipsize(TextUtils.TruncateAt.END);
                tv.setText(getResources().getStringArray(R.array.spinner_choose_text_size)[position]);
                tv.setTextSize(20);
                return convertView;

            }
        };
        spE1.setAdapter(adapter1);
        spE1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sp1=i;
                switch (i){
                    case 0: t99.setTextSize(14); break;
                    case 1: t99.setTextSize(17); break;
                    case 2: t99.setTextSize(20); break;
                    case 3: t99.setTextSize(23); break;
                    default: t99.setTextSize(18);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
  /*      spE2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp2=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        eR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                em = emE.getText().toString();
                ep1= epE1.getText().toString();
                ep2= epE2.getText().toString();
                if( !(em.length()>0 && ep1.length()>0 && ep2.length()>0) ){
                    Toast.makeText(RegisterActivity.this, "All the fields are mandatory. Kindly Enter the details properly.", Toast.LENGTH_SHORT).show();
                }
                else if(!(ep1.trim().equals(ep2.trim()))){
                    Toast.makeText(RegisterActivity.this, "Password and Confirm Password Does not match", Toast.LENGTH_SHORT).show();
                }else{


                    mAuth.createUserWithEmailAndPassword(em, ep1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(RegisterActivity.this, "User Registered Successfully. Kindly Login to Continue.", Toast.LENGTH_SHORT).show();
                                        FirebaseUser firebaseUser=mAuth.getCurrentUser();
                                        String UserId = firebaseUser.getUid();
/*                                        SharedPreferences sharedPreferences = getSharedPreferences("UP",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        int t11,t12;
                                        switch (sp1){
                                            case 0: t99.setTextSize(14; break;
                                            case 1: t99.setTextSize(17; break;
                                            case 2: t99.setTextSize(20; break;
                                            case 3: t99.setTextSize(23; break;
                                            default: t99.setTextSize(18;
                                        }
                                        t12=sp2;
                                        myEdit.putInt("size",t11).commit();
                                        myEdit.putInt("color",t12).commit();
                                        myEdit.apply();*/


                                        DBReport d1= new DBReport(UserId,sp1,sp2);
//                                        DBReport d2= new DBReport(UserId,sp1,sp2,firebaseUser.getEmail());
                                        mDatabase.child("Users").child(firebaseUser.getUid()).setValue(d1).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        mDatabase.child("UserDet").child(firebaseUser.getUid()).setValue(d2)
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "Something Went Wrong , Try Again Later", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        mAuth.signOut();
                                        startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                                        finish();

                                    }
                                    else
                                        {
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Toast.makeText(RegisterActivity.this, "User with this Email Address Already Exits", Toast.LENGTH_SHORT).show();}
                                        else{Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();}

                                    }
                                }
                            });

                }



            }
        });



    }
    
    
    public void setColor(int a) {
        switch (a) {
            case 0:
                t99.setBackgroundColor(Color.parseColor("#FFFFFF"));
                t99.setTextColor(Color.parseColor("#000000")); break;
//                    White
            case 1:
                t99.setBackgroundColor(Color.parseColor("#6666ff"));
                t99.setTextColor(Color.parseColor("#FFFFFF"));break;
//                    Blue
            case 2:
                t99.setBackgroundColor(Color.parseColor("#ffff80"));
                t99.setTextColor(Color.parseColor("#000000"));break;
//                Yellow
            case 3:
                t99.setBackgroundColor(Color.parseColor("#cccccc"));
                t99.setTextColor(Color.parseColor("#000000"));break;
//                Silver
            case 4:
                t99.setBackgroundColor(Color.parseColor("#cc66ff"));
                t99.setTextColor(Color.parseColor("#000000"));break;
//                Purple
            case 5:
                t99.setBackgroundColor(Color.parseColor("#66ff99"));
                t99.setTextColor(Color.parseColor("#000000"));break;
//                Green
            case 6:
                t99.setBackgroundColor(Color.parseColor("#42423d"));
                t99.setTextColor(Color.parseColor("#FFFFFF"));break;
//Night
            case 7:
                t99.setBackgroundColor(Color.parseColor("#42423d"));
                t99.setTextColor(Color.parseColor("#FFFFFF"));break;
//Dark
            default:
                t99.setBackgroundColor(Color.parseColor("#FF0000"));break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_white:
                if (checked)
                    sp2=0;
                    break;
            case R.id.radio_blue:
                if (checked)
                    sp2=1;
                    break;
            case R.id.radio_yellow:
                if (checked)
                    sp2=2;
                    break;
            case R.id.radio_silver:
                if (checked)
                    sp2=3;
                    break;
            case R.id.radio_purple:
                if (checked)
                    sp2=4;
                    break;
            case R.id.radio_green:
                if (checked)
                    sp2=5;
                    break;
            case R.id.radio_black_n:
                if (checked)
                    sp2=6;
                    break;
            case R.id.radio_black_d:
                if (checked)
                    sp2=7;
                    break;

        }
        setColor(sp2);
    }
}