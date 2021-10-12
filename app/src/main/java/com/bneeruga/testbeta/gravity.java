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

    private TextView xvalue,yvalue,zvalue;
    private SensorManager sensorManager;
    private Sensor gravity;
    private boolean isGravityAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelo);

        xvalue = findViewById(R.id.textView7);
        yvalue = findViewById(R.id.textView8);
        zvalue = findViewById(R.id.textView3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            isGravityAvailable = true;
        }else{
            xvalue.setText("Gravity not present");
            yvalue.setText("Gravity not present");
            zvalue.setText("Gravity not present");
            isGravityAvailable = false;
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



