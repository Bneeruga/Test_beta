package com.bneeruga.testbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class References extends AppCompatActivity {

    private TextView refe;
    private TextView refe2;
    private TextView refe3;
    private TextView refe4;
    private TextView refe5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        refe = findViewById(R.id.textView31);
        refe.setMovementMethod(LinkMovementMethod.getInstance());

        refe2 = findViewById(R.id.textView32);
        refe2.setMovementMethod(LinkMovementMethod.getInstance());

        refe3 = findViewById(R.id.textView33);
        refe3.setMovementMethod(LinkMovementMethod.getInstance());

        refe4 = findViewById(R.id.textView34);
        refe4.setMovementMethod(LinkMovementMethod.getInstance());

        refe5 = findViewById(R.id.textView35);
        refe5.setMovementMethod(LinkMovementMethod.getInstance());
    }
}