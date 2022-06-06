package com.example.androidfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("時鐘").setContent(R.id.tabTime));
        tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("鬧鐘").setContent(R.id.tabAlarm));
        tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("計時器").setContent(R.id.tabTimer));
        tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("秒表").setContent(R.id.tabStopWatch));
    }

    private TabHost tabHost;
}