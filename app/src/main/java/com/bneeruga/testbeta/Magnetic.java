package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Magnetic extends AppCompatActivity {

    private TextView magneticValuex, magneticValuey, magneticValuez;
    private ImageView arrow;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagneticField;
    private Sensor magnetic;
    private boolean isMagneticAvailable;

    private float[] floatGravity = new float[3];
    private float[] floatGeoMagnetic = new float[3];

    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);


        magneticValuex = findViewById(R.id.textView5);
        magneticValuey = findViewById(R.id.textView6);
        magneticValuez = findViewById(R.id.textView);

        arrow = findViewById(R.id.arrowView);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//
//        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
//            magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//            isMagneticAvailable = true;
//        }else{
//            magneticValuex.setText("Magnetic not present");
//            magneticValuey.setText("Magnetic not present");
//            magneticValuez.setText("Magnetic not present");
//            isMagneticAvailable = false;
//        }
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isMagneticAvailable)
//            sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(isMagneticAvailable)
//            sensorManager.unregisterListener(this);
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//        floatGravity = sensorEvent.values;
//
//        SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
//        SensorManager.getOrientation(floatRotationMatrix, floatOrientation);
//
//        arrow.setRotation((float) (-floatOrientation[0]*180/3.14159));
//
//        magneticValuex.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0 + "   micro-Tesla (uT) ");
//        magneticValuey.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0  + "  micro-Tesla (uT) ");
//        magneticValuez.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0  + "  micro-Tesla (uT) ");
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//
//    }
//}

        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        SensorEventListener sensorEventListenerAccelrometer = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                floatGravity = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

                arrow.setRotation((float) (-floatOrientation[0] * 180 / 3.14159));

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        SensorEventListener sensorEventListenerMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                floatGeoMagnetic = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

                arrow.setRotation((float) (-floatOrientation[0] * 180 / 3.14159));
                magneticValuex.setText(Math.round(event.values[0] * 1000) / 1000.0 + "   micro-Tesla ");
                magneticValuey.setText(Math.round(event.values[0] * 1000) / 1000.0 + "  micro-Tesla ");
                magneticValuez.setText(Math.round(event.values[0] * 1000) / 1000.0 + "  micro-Tesla ");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(sensorEventListenerAccelrometer, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerMagneticField, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }
//
//public void ResetButton(View view){
//        arrow.setRotation(0);
//        }

}