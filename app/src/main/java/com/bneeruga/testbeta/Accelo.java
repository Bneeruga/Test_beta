package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Accelo extends AppCompatActivity implements SensorEventListener {

    private TextView xvalue,yvalue,zvalue;
    private SensorManager sensorManager;
    private Sensor accelometer;
    private boolean isAccelometerAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelo);

        xvalue = findViewById(R.id.xtextView);
        yvalue = findViewById(R.id.ytextView);
        zvalue = findViewById(R.id.ztextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelometerAvailable = true;
        }else{
            xvalue.setText("Accelerometer not present");
            yvalue.setText("Accelerometer not present");
            zvalue.setText("Accelerometer not present");
            isAccelometerAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xvalue.setText(sensorEvent.values[0] + "m/s^2");
        yvalue.setText(sensorEvent.values[1] + "m/s^2");
        zvalue.setText(sensorEvent.values[2] + "m/s^2");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAccelometerAvailable)
            sensorManager.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isAccelometerAvailable)
            sensorManager.unregisterListener(this);
    }
}