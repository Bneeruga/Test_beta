package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class gravity extends AppCompatActivity implements SensorEventListener {

    private TextView gravityvaluex,gravityvaluey,gravityvaluez;
    private SensorManager sensorManager;
    private Sensor gravity;
    private boolean isGravityAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        gravityvaluex = findViewById(R.id.textView3);
        gravityvaluey = findViewById(R.id.textView4);
        gravityvaluez = findViewById(R.id.textView7);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            gravity = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isGravityAvailable = true;
        }else{
            gravityvaluex.setText("Gravity not present");
            gravityvaluey.setText("Gravity not present");
            gravityvaluez.setText("Gravity not present");
            isGravityAvailable = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        gravityvaluex.setText(sensorEvent.values[0] + "m/s^2");
        gravityvaluey.setText(sensorEvent.values[1] + "m/s^2");
        gravityvaluez.setText(sensorEvent.values[2] + "m/s^2");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isGravityAvailable)
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isGravityAvailable)
            sensorManager.unregisterListener(this);
    }
}