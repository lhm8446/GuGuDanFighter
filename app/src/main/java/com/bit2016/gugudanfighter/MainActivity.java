package com.bit2016.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_startGame).setOnClickListener(this);
        findViewById(R.id.button_record).setOnClickListener(this);
        findViewById(R.id.button_help).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.button_startGame:{
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_record:{
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_help:{
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
