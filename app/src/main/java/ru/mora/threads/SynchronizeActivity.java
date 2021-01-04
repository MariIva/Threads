package ru.mora.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SynchronizeActivity extends AppCompatActivity {

    CreditCard card;
    int sub_money;
    Button bt_s_exe;
    TextView tv_ost;
    TextInputLayout te_money, te_sub_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize);

        bt_s_exe = (Button) findViewById(R.id.bt_s_exe);
        tv_ost = (TextView) findViewById(R.id.tv_ost);
        te_money = (TextInputLayout) findViewById(R.id.te_money);
        te_sub_money = (TextInputLayout) findViewById(R.id.te_sub_money);

        bt_s_exe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // очистка поля вывода
                    tv_ost.setText("");
                    // получение количество снимаемых средств
                    sub_money = Integer.parseInt(te_sub_money.getEditText().getText().toString());
                    // получения баланса карты и её создание
                    card = new CreditCard(Integer.parseInt(te_money.getEditText().getText().toString()));
                    // создание и запуск потоков
                    CardThread thread=new CardThread();
                    CardThread thread1=new CardThread();
                    CardThread thread2=new CardThread();
                    CardThread thread3=new CardThread();
                    thread.start();
                    thread1.start();
                    thread2.start();
                    thread3.start();
                }
                catch (NumberFormatException e){

                }
            }
        });
    }

    // объект для обновления активности
    Handler handler = new Handler() {   // создание хэндлера
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_ost.setText(tv_ost.getText().toString() + card.getMoney() + "\n");
        }
    };

    // класс карты
    class CreditCard{
        private int money;
        public CreditCard(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        // не синхронизированный метод списания средств
        public void subMoney(int sub) {
            if (money - sub > 0) { // достаточно ли средств ?
                try {
                    Thread.sleep((long) (1000 * Math.random()));  // имитируем задержку банкомата
                } catch (InterruptedException e) {}
                money -= sub;  // снимаем со счета нужную сумму
            }
        }

    }

    // класс второго потока, наследник класса Thread
    class CardThread extends Thread {
        @Override
        public void run() {
            // синхронизация по объекту
            synchronized (card) {
                // снятие средств
                card.subMoney(sub_money);
                // передача сообщения хендлеру
                handler.sendEmptyMessage(0);
            }
        }
    }
}
