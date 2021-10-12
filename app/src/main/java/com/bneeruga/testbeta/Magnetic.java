package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Magnetic extends AppCompatActivity implements SensorEventListener {

    private TextView magneticValuex,magneticValuey,magneticValuez;
    private SensorManager sensorManager;
    private Sensor magnetic;
    private boolean isMagneticAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);


        magneticValuex = findViewById(R.id.textView5);
        magneticValuey = findViewById(R.id.textView6);
        magneticValuez = findViewById(R.id.textView);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            isMagneticAvailable = true;
        }else{
            magneticValuex.setText("Magnetic not present");
            magneticValuey.setText("Magnetic not present");
            magneticValuez.setText("Magnetic not present");
            isMagneticAvailable = false;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(isMagneticAvailable)
            sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isMagneticAvailable)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        magneticValuex.setText(sensorEvent.values[0] + "micro-Tesla (uT) ");
        magneticValuey.setText(sensorEvent.values[1] + "micro-Tesla (uT) ");
        magneticValuez.setText(sensorEvent.values[2] + "micro-Tesla (uT) ");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}