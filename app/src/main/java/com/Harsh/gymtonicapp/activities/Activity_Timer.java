package com.Harsh.gymtonicapp.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Harsh.gymtonicapp.R;

public class Activity_Timer extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStartTimer;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 60000; // Change this to your desired initial countdown time in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tvTimer = findViewById(R.id.tvTimer);
        btnStartTimer = findViewById(R.id.btnStartTimer);

        btnStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                btnStartTimer.setText("Start Timer");
            }
        }.start();

        timerRunning = true;
        btnStartTimer.setText("Pause Timer");
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        btnStartTimer.setText("Start Timer");
    }

    private void updateTimer() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeft = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        tvTimer.setText(timeLeft);
    }
}
