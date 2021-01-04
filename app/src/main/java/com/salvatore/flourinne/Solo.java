package com.salvatore.flourinne;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class Solo extends AppCompatActivity implements View.OnClickListener {
EditText title,objective,venue,timings;
Button submit,image;
TextView imagetext;
String titlet,obj,ven,tim;
//Bundle bundle = getIntent().getExtras();
//String person = bundle.getString("person");
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solo);
        title = (EditText) findViewById(R.id.title);
        objective = (EditText) findViewById(R.id.objective);
        venue = (EditText) findViewById(R.id.venue);
        timings = (EditText) findViewById(R.id.timings);
        submit = (Button) findViewById(R.id.submitsolo);
        image = (Button) findViewById(R.id.image);
        imagetext = (TextView) findViewById(R.id.imagetext);
        imagetext.setText("Click here to add an image");
        submit.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        title.setOnClickListener(this);
        objective.setOnClickListener(this);
        venue.setOnClickListener(this);
        timings.setOnClickListener(this);
        //readwrite();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.title:
                titlet= String.valueOf(title.getText());
                Toast.makeText(this,titlet,Toast.LENGTH_LONG).show();
                break;
            case R.id.objective:
                obj= String.valueOf(objective.getText());
                break;
            case R.id.venue:
                ven= String.valueOf(venue.getText());
                break;
            case R.id.timings:
                tim= String.valueOf(timings.getText());
                break;
            case R.id.submitsolo:
               readwrite(titlet,obj,ven,tim);

        }
    }
    @IgnoreExtraProperties
    public class User {

        public String title;
        public String obj;
        public String ven;
        public String tim;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public User() {
        }

        public User(String title, String obj,String ven,String tim) {
            this.title = title;
            this.obj = obj;
            this.ven = ven;
            this.tim = tim;
        }
    }

    public void readwrite(String tit,String ob,String ve,String ti)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //DatabaseReference myRef = database.getReference("suray");
/*Toast toast;
        //myRef.setValue("Hello, World!");
        // [END write_message]

        // [START read_message]
        // Read from the database
       // final Toast finalToast = toast;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(getApplicationContext(),"accepted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(),"cancelled",Toast.LENGTH_LONG).show();
            }
        });*/



        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        account.getAccount();
        String email=account.getEmail();
       /* DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.setValue("VAlue kept");
        String userID = myRef.push().getKey();
        User user = new User(tit,ob,ve,ti);
        myRef.child(userID).setValue(user);*/
        Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
        String surya = "nudurupatisurya@gmail.com";
        String Himavanth = "phimavanth@gmail.com";

        if(((account.getEmail()).compareTo(surya))==0)
        {
            //Toast.makeText(getApplicationContext(),"hammaya",Toast.LENGTH_SHORT).show();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
            myRef.setValue("VAlue kept");
            String userID = myRef.push().getKey();
            User user = new User(tit,ob,ve,ti);
            myRef.child(userID).setValue(user);
        }
        else if(((account.getEmail()).compareTo(Himavanth))==0)
        {
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("user2");
            myRef.setValue("VAlue kept");

            String userID = myRef.push().getKey();
            User user = new User(tit,ob,ve,ti);
            //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
            myRef.child(userID).setValue(user);

        }



    }
}
