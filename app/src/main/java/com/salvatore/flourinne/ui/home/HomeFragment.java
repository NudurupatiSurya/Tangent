package com.salvatore.flourinne.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.salvatore.flourinne.MainActivity;
import com.salvatore.flourinne.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HomeFragment extends Fragment implements View.OnClickListener {
Button plus;
Bundle bundle;
    private HomeViewModel homeViewModel;
    MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });


        final TextView textView2 = root.findViewById(R.id.post);
        textView2.setText("Create Event");
        plus=root.findViewById(R.id.plus);
        plus.setOnClickListener(this);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        account.getAccount();
        String namee=account.getDisplayName();
        TextView name =root.findViewById(R.id.name);
        name.setText(namee);
        ImageView icon = root.findViewById(R.id.icon);
        Uri iconurl = account.getPhotoUrl();
       icon.setImageURI(account.getPhotoUrl());

        Picasso.with(getContext()).load(iconurl).into(icon);
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("users");
        Toast toast;
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

               Toast.makeText(getContext(),value,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getContext(),"cancelled",Toast.LENGTH_LONG).show();
            }
        });*/
        return root;
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.plus)
        {
            Intent intent=new Intent(view.getContext(),Main4Activity.class);
            /*Bundle bundle = new Bundle();
            bundle.putString("person",mainActivity.person);*/
            view.getContext().startActivity(intent);

        }
    }



}

