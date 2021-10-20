package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Accelo extends AppCompatActivity implements SensorEventListener {

    private TextView xvalue,yvalue,zvalue,prev,curr,change;
    private SensorManager sensorManager;
    private ProgressBar proBar;
    private Sensor accelometer;
    private boolean isAccelometerAvailable;
    private double accelerationPrevValue;
    private double accelerationCurrValue;
    private int pointsOnGraph = 5;
    private int countInterval = 0;
    private Viewport viewPort;

    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });


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

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        xvalue.setText(Math.round(sensorEvent.values[0]* 1000)/1000.0 + "   m/s^2");
        yvalue.setText(Math.round(sensorEvent.values[1]* 1000)/1000.0 + "   m/s^2");
        zvalue.setText(Math.round(sensorEvent.values[2]* 1000)/1000.0 + "   m/s^2");

        accelerationCurrValue = Math.sqrt( (x * x + y * y + z * z ));
        double changeInAcceleration = Math.abs(accelerationCurrValue - accelerationPrevValue);
        accelerationPrevValue = accelerationCurrValue;

        prev = findViewById(R.id.prevAcc);
        curr = findViewById(R.id.currAcc);
        change = findViewById(R.id.changeAcc);

        prev.setText("Prev :  " + (int)accelerationPrevValue );
        curr.setText("Curr :  " + (int)accelerationCurrValue );
        change.setText("Change :  " + (int)changeInAcceleration );
        proBar = findViewById(R.id.progressBar);
        proBar.setProgress( (int) changeInAcceleration );



        pointsOnGraph++;
        if(pointsOnGraph == 200){
            pointsOnGraph =1;
            series.resetData(new DataPoint[]{ new DataPoint(1,0)});
        }
        series.appendData( new DataPoint(pointsOnGraph, changeInAcceleration), true, pointsOnGraph);


        GraphView graph = (GraphView) findViewById(R.id.graph);
        viewPort = graph.getViewport();
        viewPort.setMaxX(pointsOnGraph);
        viewPort.setMinX(pointsOnGraph - 200 );
        viewPort.setScalable(true);
        viewPort.setXAxisBoundsManual(true);

        graph.addSeries(series);


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