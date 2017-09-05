package com.surabheesinha.mobilelocatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by surabheesinha on 9/3/17.
 */

public class About extends AppCompatActivity {

    Button LetsStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        //Intent intent = getIntent();

        LetsStart = (Button) findViewById(R.id.LetsStart);
        LetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SendMessage.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
