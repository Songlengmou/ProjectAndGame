package com.example.syp.makecarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 全面完成数字记忆小游戏
 */
public class MainActivity extends AppCompatActivity {

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = new MainView(this);
        setContentView(mainView);
    }
}
