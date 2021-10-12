package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Light extends AppCompatActivity implements SensorEventListener {

    private TextView lightValue;
    private SensorManager sensorManager;
    private Sensor light;
    private boolean isLightAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);


        lightValue = findViewById(R.id.textView2);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            isLightAvailable = true;
        }else{
            lightValue.setText("Light not present");
            isLightAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        lightValue.setText(sensorEvent.values[0] + "lux");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isLightAvailable)
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isLightAvailable)
            sensorManager.unregisterListener(this);
    }
}