package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_no_thread;
    Button bt_async_task;
    Button bt_thread;
    Button bt_runnable;
    Button bt_no_sinch;
    Button bt_sinch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_no_thread = findViewById(R.id.bt_no_thread);
        bt_async_task = findViewById(R.id.bt_async_task);
        bt_thread = findViewById(R.id.bt_thread);
        bt_runnable = findViewById(R.id.bt_runnable);
        bt_no_sinch = findViewById(R.id.bt_no_sinch);
        bt_sinch = findViewById(R.id.bt_sinch);

        bt_no_thread.setOnClickListener(this);
        bt_async_task.setOnClickListener(this);
        bt_thread.setOnClickListener(this);
        bt_runnable.setOnClickListener(this);
        bt_no_sinch.setOnClickListener(this);
        bt_sinch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            case R.id.bt_no_thread:
                intent = new Intent(MainActivity.this, NoThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_async_task:
                intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_thread:
                intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_runnable:
                intent = new Intent(MainActivity.this, RunnableActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_no_sinch:
                intent = new Intent(MainActivity.this, NoSynchronizeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_sinch:
                intent = new Intent(MainActivity.this, SynchronizeActivity.class);
                startActivity(intent);
                break;
        }
    }
}