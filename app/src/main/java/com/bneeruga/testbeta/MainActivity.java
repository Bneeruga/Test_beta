package com.bneeruga.testbeta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Button accButton;
    private Button gyroButton;
    private Button lightButton;
    private Button gravityButton;
    private Button magneticButton;
    private Button proximityButton;
    private Button reference;
    private DrawerLayout drawer;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accButton = findViewById(R.id.btn1);
        accButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Accelo.class);
                startActivity(intent);
            }
        });


        gyroButton = findViewById(R.id.btn2);
        gyroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gyro.class);
                startActivity(intent);
            }
        });

        lightButton = findViewById(R.id.btn3);
        lightButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Light.class);
                startActivity(intent);
            }
        });

        gravityButton = findViewById(R.id.btn4);
        gravityButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, gravity.class);
                startActivity(intent);
            }
        });

        magneticButton = findViewById(R.id.btn5);
        magneticButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Magnetic.class);
                startActivity(intent);
            }
        });

        proximityButton = findViewById(R.id.btn6);
        proximityButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, proximity.class);
                startActivity(intent);
            }
        });

        reference = findViewById(R.id.button);
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, References.class);
                startActivity(intent);
            }
        });
    }


}