package com.iitism.buildabit.Authentication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitism.buildabit.HomePageActivity;
import com.iitism.buildabit.MainActivity;
import com.iitism.buildabit.R;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
  EditText name,email,password,confirmpassword,clubname;
  Button signup;
  FirebaseAuth firebaseAuth;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.edt_txt_sign_up_name);
        email=findViewById(R.id.edt_txt_sign_up_email);
        password=findViewById(R.id.edt_txt_sign_up_password);
        confirmpassword=findViewById(R.id.edt_txt_sign_up_confirm_password);
        clubname=findViewById(R.id.edt_txt_sign_up_club);

        signup=findViewById(R.id.btn_sign_up);
        signup.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Profile");

    }

    @Override
    public void onClick(View view) {
        if(view ==signup)
        {
            final String xname,xemail,xpassword,xconfirmpassword,xclub;

            xname=name.getText().toString().trim();
            xemail=email.getText().toString().trim();
            xpassword=password.getText().toString().trim();
            xconfirmpassword=confirmpassword.getText().toString().trim();
            xclub=clubname.getText().toString().trim();

            if (xemail.isEmpty()||xname.isEmpty()||xpassword.isEmpty()||xconfirmpassword.isEmpty()||xclub.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Please enter all details",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(!xpassword.equals(xconfirmpassword))
            {
                Toast.makeText(getApplicationContext(),"Passwords do not match..",Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                firebaseAuth.createUserWithEmailAndPassword(xemail,xpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {    String uid=databaseReference.push().getKey();
                           databaseReference.child(uid).child("Name").setValue(xname).toString();
                           databaseReference.child(uid).child("Club Name").setValue(xclub).toString();
                           databaseReference.child(uid).child("Email").setValue(xemail).toString();
                           Toast.makeText(getApplicationContext(), "Successfully signed in", Toast.LENGTH_SHORT).show();
                           Intent i=new Intent(getApplicationContext(), HomePageActivity.class);
                           finish();
                           startActivity(i);
                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(), " Unable to sign in", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
        }
    }
}
