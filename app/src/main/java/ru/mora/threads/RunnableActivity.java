package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class RunnableActivity extends AppCompatActivity {

    LinearProgressIndicator pi_r;
    Button bt_r_click, bt_r_load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runnable);
        pi_r = (LinearProgressIndicator) findViewById(R.id.pi_r);
        bt_r_click = findViewById(R.id.bt_r_click);
        bt_r_load = findViewById(R.id.bt_r_load);

        bt_r_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });
        bt_r_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создание и запуск потоков
                Thread myRunnable=new Thread(new MyRunnable());
                myRunnable.start();
            }
        });
    }

    // объект для обновления активности
    Handler handler = new Handler() {   // создание хэндлера
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0) {
                pi_r.setVisibility(LinearProgressIndicator.INVISIBLE);
            }
            if (msg.what==1) {
                pi_r.setVisibility(LinearProgressIndicator.VISIBLE);
            }
            if (msg.what==2) {
                Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_SHORT).show();
            }
        }
    };


    // класс второго потока, реализующий интерфейс Runnable
    class MyRunnable implements Runnable {
        //код выполняемый в параллельном потоке
        @Override
        public void run() {
            // передача сообщения хендлеру
            handler.sendEmptyMessage(1);
            for (int i = 0; i < 10; i ++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // передача сообщения хендлеру
            handler.sendEmptyMessage(0);
            // передача сообщения хендлеру
            handler.sendEmptyMessage(2);
        }
    }
}