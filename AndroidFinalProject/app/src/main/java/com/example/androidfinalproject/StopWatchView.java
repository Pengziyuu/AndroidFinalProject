package com.example.androidfinalproject;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatchView extends LinearLayout {

    public StopWatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvHour = (TextView) findViewById(R.id.timeHour);
        tvHour.setText("0");
        tvMin = (TextView) findViewById(R.id.timeMin);
        tvMin.setText("0");
        tvSec = (TextView) findViewById(R.id.timeSec);
        tvSec.setText("0");
        tvMsec = (TextView) findViewById(R.id.timeMSec);
        tvMsec.setText("0");

        btnLap = (Button) findViewById(R.id.btnSWLap);
        btnLap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (watchViewCount == 0) {
                    adapter.insert("圈數   單圈時間", 0);
                }
                adapter.insert(String.format("  %d       %d:%d:%d.%d",
                        watchViewCount,
                        tenMSecs/100/60/60,
                        tenMSecs/100/60%60,
                        tenMSecs/100%60,
                        tenMSecs%100), 1);
                watchViewCount++;
            }
        });
        btnPause = (Button) findViewById(R.id.btnSWPause);
        btnPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stopTimer();

                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
                btnLap.setVisibility(View.GONE);
                btnReset.setVisibility(View.VISIBLE);
            }
        });
        btnReset = (Button) findViewById(R.id.btnSWReset);
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stopTimer();
                tenMSecs = 0;
                watchViewCount = 0;
                adapter.clear();

                btnLap.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                btnReset.setVisibility(View.GONE);
                btnResume.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);
            }
        });
        btnResume = (Button) findViewById(R.id.btnSWResume);
        btnResume.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startTimer();
                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.GONE);
                btnLap.setVisibility(View.VISIBLE);
            }
        });
        btnStart = (Button) findViewById(R.id.btnSWStart);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startTimer();
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnLap.setVisibility(View.VISIBLE);
            }
        });
        btnLap.setVisibility(View.GONE);
        btnPause.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);

        lvTimeList = (ListView) findViewById(R.id.lvWatchTimeList);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        lvTimeList.setAdapter(adapter);

        showTimeTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_WHAT_SHOW_TIME);
            }
        };
        timer.schedule(showTimeTask, 200, 200);
    }

    private void startTimer() {
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    tenMSecs++;
                }
            };
            timer.schedule(timerTask, 10, 10);
        }
    }

    private void stopTimer() {
        if(timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private int watchViewCount = 0;
    private int tenMSecs = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private TimerTask showTimeTask = null;

    private TextView tvHour,tvMin,tvSec,tvMsec;
    private Button btnStart, btnPause, btnResume, btnLap, btnReset;
    private ListView lvTimeList;
    private ArrayAdapter<String> adapter;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_WHAT_SHOW_TIME:
                    tvHour.setText(tenMSecs/100/60/60+"");
                    tvMin.setText(tenMSecs/100/60%60+"");
                    tvSec.setText(tenMSecs/100%60+"");
                    tvMsec.setText(tenMSecs%100+"");
                    break;
                default:
                    break;
            }
        }
    };

    private static  final  int MSG_WHAT_SHOW_TIME = 1;

    public void onDestroy() {
        timer.cancel();
    }
}
