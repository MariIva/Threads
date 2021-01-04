package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class AsyncTaskActivity extends AppCompatActivity {

    // полоса прогресса от 0 до 100
    LinearProgressIndicator pi_at;
    Button bt_at_click, bt_at_load;
    TextView tv_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        pi_at = (LinearProgressIndicator) findViewById(R.id.pi_at);
        tv_at = (TextView) findViewById(R.id.tv_at);
        bt_at_click = (Button) findViewById(R.id.bt_at_click);
        bt_at_load = (Button) findViewById(R.id.bt_at_load);

        bt_at_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLick", Toast.LENGTH_SHORT).show();
            }
        });
        bt_at_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создание и запуск второго потока
                new MyAsyncTask().execute();
            }
        });
    }

    // класс второго потока
    // Void - тип входных параметров (передаются в методе execute() )
    // Integer - тип данных для обновления активности
    // String - тип данных результата работы второго потока
    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        // метод выполняемый перед запуском кода второго потока
        // имеет доступ к активности
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pi_at.setVisibility(ProgressBar.VISIBLE);
        }

        // код второго потока
        // получает массив типа Void
        // возвращает переменную типа String
        // НЕ имеет доступа к активности
        @Override
        protected String doInBackground(Void... args) {
            for (int i = 0; i < 100; i += 1) {
                try {
                    // остановка потока на 0.1 секунды
                    Thread.sleep(100);
                    // вызов метода onProgressUpdate
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // вызов метода onProgressUpdate
            publishProgress(100);
            return null;
        }

        // метод выполняемый после выполнения кода второго потока
        // получает переменную типа String
        // имеет доступ к активности
        @Override
        protected void onPostExecute(String image) {
            Toast.makeText(getApplicationContext(), "Load", Toast.LENGTH_SHORT).show();
        }

        // обновление активности во время выполнения второго потока
        // получает массиы типа Integer
        // имеет доступ к активности
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pi_at.setProgress(values[0]);
            tv_at.setText("Выполнено : " + values[0] + "/100");
        }
    }
}
