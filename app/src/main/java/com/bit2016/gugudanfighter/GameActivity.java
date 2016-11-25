package com.bit2016.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.value;

public class GameActivity extends AppCompatActivity {

    private static final int TIME_LIMIT = 30;
    private Timer timer = new Timer();
    private TextView tvLastTime;
    private int totalCount = 0;
    private int coCount = 0;
    private TextView operandView1;
    private TextView operandView2;
    private int result= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvLastTime = (TextView) findViewById(R.id.restTime);

        timer.schedule(new GameTimerTask(), 1000, 1000);

        question();

        click();

    }

    private void updateLastTime(int seconds) {
        tvLastTime.setText("" + (TIME_LIMIT - seconds));
    }

    private class GameTimerTask extends TimerTask {

        private int seconds;

        @Override
        public void run() {
            seconds++;
            if (seconds >= TIME_LIMIT) {
                timer.cancel();
                Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                startActivity(intent);
                finish();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateLastTime(seconds);
                }
            });
        }
    }

    private static int multiplication() {
        return (int) (Math.random() * (9 - 1 + 1)) + 1;
    }

    private void question() {
        int OP1 = multiplication();
        int OP2 = multiplication();

        operandView1 = (TextView) findViewById(R.id.oprand1);
        operandView2 = (TextView) findViewById(R.id.oprand2);

        operandView1.setText("" + OP1);
        operandView2.setText("" + OP2);

        HashSet<Integer> hashSet = new HashSet<>();

        hashSet.add(OP1 * OP2);
        result = OP1 * OP2;

        while (hashSet.size() < 9) {
            int ex1 = multiplication();
            int ex2 = multiplication();

            hashSet.add(ex1 * ex2);
        }

        Iterator<Integer> iterator = hashSet.iterator();

        final TextView[] textViewArray = new TextView[9];

        int i = 0;

        while (iterator.hasNext()) {
            String textViewID = "correct" + (i + 1);
            int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
            textViewArray[i] = (TextView) findViewById(resID);
            textViewArray[i].setText("" + iterator.next());

            i = i + 1;
        }
    }

    private void click(){

        findViewById(R.id.correct1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button)view;
                if(result == Integer.parseInt(btn.getText().toString())){
                    coCount = coCount+1;
                    totalCount = totalCount +1;
                    TextView tv1 = (TextView)findViewById(R.id.correctCount1);
                    TextView tv2 = (TextView)findViewById(R.id.correctCount2);

                    tv1.setText(""+coCount);
                    tv2.setText(""+totalCount);

                    question();

                }else{
                    totalCount = totalCount +1;

                    TextView tv2 = (TextView)findViewById(R.id.correctCount2);
                    tv2.setText(""+totalCount);
                    question();
                }
            }
        });
    }
}
