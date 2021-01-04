package com.salvatore.flourinne.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.salvatore.flourinne.Solo;
import com.salvatore.flourinne.R;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
Button group,solo;
TextView groupt,solot;
/*Bundle bundle = getIntent().getExtras();
String person = bundle.getString("person");*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        groupt=(TextView) findViewById(R.id.grouptext);
        groupt.setText("Create Event Solely");
        solot = (TextView) findViewById(R.id.solotext);
        solot.setText("Create by Group");
        group = (Button) findViewById(R.id.group);
        group.setOnClickListener(this);
        solo=(Button) findViewById(R.id.solo);
        solo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.solo:
                Intent intent1 = new Intent(view.getContext(), Solo.class);
                //Bundle bundlee = new Bundle();
                //bundlee.putString("person",person);
                this.startActivity(intent1);
            case R.id.group:
                Intent intent2 = new Intent(view.getContext(),Solo.class);
                this.startActivity(intent2);
        }
    }
}
