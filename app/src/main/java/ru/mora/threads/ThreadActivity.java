package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import android.os.Handler;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class ThreadActivity extends AppCompatActivity{


    CircularProgressIndicator pi_t;
    Button bt_t_click, bt_t_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);


        pi_t = (CircularProgressIndicator) findViewById(R.id.pi_t);
        bt_t_click = findViewById(R.id.bt_t_click);
        bt_t_load = findViewById(R.id.bt_t_load);

        bt_t_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });
        bt_t_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создание и запуск потоков
                MyThread myThread=new MyThread();
                myThread.start();
            }
        });
    }

    // объект для обновления активности
    Handler handler = new Handler() {   // создание хэндлера
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0) {
                pi_t.setVisibility(CircularProgressIndicator.INVISIBLE);
            }
            if (msg.what==1) {
                pi_t.setVisibility(CircularProgressIndicator.VISIBLE);
            }
            if (msg.what==2) {
                Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // класс второго потока, наследник класса Thread
    class MyThread extends Thread {
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