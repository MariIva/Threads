package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoThreadActivity extends AppCompatActivity {

    Button bt_n_t_click, bt_n_t_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_thread);


        bt_n_t_click = findViewById(R.id.bt_n_t_click);
        bt_n_t_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });
        bt_n_t_load = findViewById(R.id.bt_n_t_load);
        bt_n_t_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSame();
                Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // типа сложное и долгое вычисление
     void loadSame(){
        try {
            // остановка потока на 5 секунд
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }
}