package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class proximity extends AppCompatActivity implements SensorEventListener {


    private TextView proximityValue;
    private SensorManager sensorManager;
    private Sensor proximity;
    private boolean isProximityAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        proximityValue = findViewById(R.id.textView9);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isProximityAvailable = true;
        }else{
            proximityValue.setText("Proximity not present");
            isProximityAvailable = false;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        proximityValue.setText(sensorEvent.values[0] + "     cm");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(isProximityAvailable)
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isProximityAvailable)
            sensorManager.unregisterListener(this);
    }
}