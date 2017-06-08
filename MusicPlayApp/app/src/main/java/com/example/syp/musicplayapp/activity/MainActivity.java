package com.example.syp.musicplayapp.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.databinding.ActivityMainBinding;
import com.example.syp.musicplayapp.presario.Presenter;

/**
 * Created by syp on 17-5-26.
 */

public class MainActivity extends AppCompatActivity {
    private Presenter presenter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        presenter = new Presenter(viewDataBinding, MainActivity.this, fragmentManager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
