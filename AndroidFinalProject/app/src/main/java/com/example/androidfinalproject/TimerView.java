package com.example.androidfinalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class TimerView extends LinearLayout {

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stopTimer();

                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });
        btnResume = (Button) findViewById(R.id.btnResume);
        btnResume.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startTimer();

                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stopTimer();

                etHour.setText("0");
                etMin.setText("0");
                etSec.setText("0");

                btnReset.setVisibility(View.GONE);
                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);

                btnHm.setVisibility(View.VISIBLE);
                btnHp.setVisibility(View.VISIBLE);
                btnMm.setVisibility(View.VISIBLE);
                btnMp.setVisibility(View.VISIBLE);
                btnSm.setVisibility(View.VISIBLE);
                btnSp.setVisibility(View.VISIBLE);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startTimer();

                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);

                btnHm.setVisibility(View.GONE);
                btnHp.setVisibility(View.GONE);
                btnMm.setVisibility(View.GONE);
                btnMp.setVisibility(View.GONE);
                btnSm.setVisibility(View.GONE);
                btnSp.setVisibility(View.GONE);
            }
        });

        etHour = (EditText) findViewById(R.id.etHour);
        etMin = (EditText) findViewById(R.id.etMin);
        etSec = (EditText) findViewById(R.id.etSec);

        etHour.setText("0");
        etHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    btnHm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x--;
                            etHour.setText(x+"");
                        }
                    });

                    btnHp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x++;
                            etHour.setText(x+"");
                        }
                    });

                    if (value>59) {
                        etHour.setText("59");
                    }else if (value<0) {
                        etHour.setText("0");
                    }

                    checkToEnableBtnStart();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        etMin.setText("0");
        etMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    btnMm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x--;
                            etMin.setText(x+"");
                        }
                    });

                    btnMp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x++;
                            etMin.setText(x+"");
                        }
                    });

                    if (value>59) {
                        etMin.setText("59");
                    }else if (value<0) {
                        etMin.setText("0");
                    }

                    checkToEnableBtnStart();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        etSec.setText("0");
        etSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    btnSm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x--;
                            etSec.setText(x+"");
                        }
                    });

                    btnSp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int x = value;
                            x++;
                            etSec.setText(x+"");
                        }
                    });

                    if (value>59) {
                        etSec.setText("59");
                    }else if (value<0) {
                        etSec.setText("0");
                    }

                    checkToEnableBtnStart();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnStart.setVisibility(View.VISIBLE);
        btnStart.setEnabled(false);
        btnPause.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);


        btnHm = findViewById(R.id.btnHm);
        btnHp = findViewById(R.id.btnHp);
        btnMm = findViewById(R.id.btnMm);
        btnMp = findViewById(R.id.btnMp);
        btnSm = findViewById(R.id.btnSm);
        btnSp = findViewById(R.id.btnSp);
    }

    private void checkToEnableBtnStart() {
        btnStart.setEnabled(
                !TextUtils.isEmpty(etHour.getText())&&Integer.parseInt(etHour.getText().toString())>0||
                (!TextUtils.isEmpty(etMin.getText())&&Integer.parseInt(etMin.getText().toString())>0)||
                (!TextUtils.isEmpty(etSec.getText())&&Integer.parseInt(etSec.getText().toString())>0));
    }

    private void startTimer() {
        if (timerTask == null) {
            allTimerCount = Integer.parseInt(etHour.getText().toString())*60*60+
                    Integer.parseInt(etMin.getText().toString())*60+
                    Integer.parseInt(etSec.getText().toString());
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    allTimerCount--;

                    handler.sendEmptyMessage(MSG_WHAT_TIME_TICK);

                    if (allTimerCount <=0) {
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                        stopTimer();
                    }
                }
            };

            timer.schedule(timerTask, 1000, 1000);
        }
    }

    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private android.os.Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case MSG_WHAT_TIME_TICK:

                    int hour = allTimerCount/60/60;
                    int min = (allTimerCount/60)%60;
                    int sec = allTimerCount%60;

                    etHour.setText(hour+"");
                    etMin.setText(min+"");
                    etSec.setText(sec+"");

                    break;
                case MSG_WHAT_TIME_IS_UP:
                    new AlertDialog.Builder(getContext())
                            .setTitle("計時器")
                            .setMessage("時間到")
                            .setNegativeButton("取消", null).show();

                    btnReset.setVisibility(View.GONE);
                    btnResume.setVisibility(View.GONE);
                    btnPause.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);

                    break;
                default:
                    break;
            }
        };
    };

    private static final int MSG_WHAT_TIME_IS_UP = 1;
    private static final int MSG_WHAT_TIME_TICK = 2;

    private int allTimerCount = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private Button btnStart, btnPause, btnResume, btnReset;
    private Button btnHm, btnHp, btnMm, btnMp, btnSm, btnSp;
    private EditText etHour, etMin, etSec;
}
