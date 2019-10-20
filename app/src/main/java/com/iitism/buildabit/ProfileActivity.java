package com.iitism.buildabit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView name,club,email;
    String xname,xclub,checkemail;

    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.txt_name);
        club=findViewById(R.id.txt_club_name);
        email=findViewById(R.id.txt_email);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        checkemail=firebaseUser.getEmail();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Profile");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(ds.child("Email").getValue(String.class).equals(checkemail))
                    {
                        xname=ds.child("Name").getValue(String.class);
                        xclub=ds.child("Club Name").getValue(String.class);

                        name.setText(xname);
                        club.setText(xclub);
                        email.setText(checkemail);
                        //set text in textviews
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
