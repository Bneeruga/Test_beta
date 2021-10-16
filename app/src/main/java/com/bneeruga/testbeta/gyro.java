package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class gyro extends AppCompatActivity implements SensorEventListener {

    private TextView xvalue,yvalue,zvalue;
    private SensorManager sensorManager;
    private Sensor gyroscope;
    private boolean isGyroscopeAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);

        xvalue = findViewById(R.id.textViewgy1);
        yvalue = findViewById(R.id.textViewgy2);
        zvalue = findViewById(R.id.textViewgy3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            isGyroscopeAvailable = true;
        }else{
            xvalue.setText("Gyroscope not present");
            yvalue.setText("Gyroscope not present");
            zvalue.setText("Gyroscope not present");
            isGyroscopeAvailable = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        xvalue.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0 + "   rad/s");
        yvalue.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0 + "   rad/s");
        zvalue.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0 + "   rad/s");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isGyroscopeAvailable)
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isGyroscopeAvailable)
            sensorManager.unregisterListener(this);
    }


}