package com.elmansouri.ccna.customnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnAfficher = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAfficher = (Button) findViewById(R.id.btn_afficher);
        btnAfficher.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_afficher){
            try {
                AdilNotification.setNoti(getApplicationContext());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
