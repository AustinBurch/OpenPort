package com.austinburch.check.openport;


import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Formatter;


public class MainActivity extends AppCompatActivity {

    private Button btnScan;
    private Intent sendAddress;

    public static final String ADDRESS = "com.austinburch.check.openport";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myImageView = findViewById(R.id.radar6);
        myImageView.setImageResource(R.drawable.radar6);


        btnScan = findViewById(R.id.btnScan);



        }

        //Button that will send ip address to Scan Activity//
        public void startScan(View view){
            sendAddress = new Intent(MainActivity.this, Scan.class);
            startActivity(sendAddress);
        }




}




