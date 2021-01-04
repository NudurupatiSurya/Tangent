package com.salvatore.flourinne.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.salvatore.flourinne.Main2Activity;
import com.salvatore.flourinne.MainActivity;
import com.salvatore.flourinne.R;
import com.salvatore.flourinne.Report;
import com.salvatore.flourinne.Success;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

public class NotificationsFragment extends Fragment implements View.OnClickListener {
        Button signout,rp,sr;
    private NotificationsViewModel notificationsViewModel;
    private Main2Activity mMain2Activity;
    //GoogleSignInAccount mGoogleSignInClient;
    //private Object Button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText("Welcome to Your Account Settings");
            }
        });
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        account.getAccount();
        String namee=account.getDisplayName();
        TextView name =root.findViewById(R.id.nameee);
        name.setText(namee);
        ImageView icon = root.findViewById(R.id.icon2);
        Uri iconurl = account.getPhotoUrl();
        icon.setImageURI(account.getPhotoUrl());
        Picasso.with(getContext()).load(iconurl).into(icon);
rp=root.findViewById(R.id.rp);
sr=root.findViewById(R.id.sr);
signout = root.findViewById(R.id.signout);
signout.setOnClickListener(this);
rp.setOnClickListener(this);
sr.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // ...
            case R.id.signout:
               signOut();
                break;
            case R.id.rp:
                Intent intent = new Intent(getContext(), Report.class);
                getContext().startActivity(intent);
                break;
            case R.id.sr:
                Intent intent2 = new Intent(getContext(), Success.class);
                getContext().startActivity(intent2);
                break;
        }
    }
    private void signOut() {
        GoogleSignInClient mGoogleSignInClient = null;
        mGoogleSignInClient.signOut()
                .addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...


                    }
                });
        revokeAccess();
    }
    private void revokeAccess() {
        GoogleSignInAccount mGoogleSignInClien;
        GoogleSignInClient mGoogleSignInClient = null;
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}