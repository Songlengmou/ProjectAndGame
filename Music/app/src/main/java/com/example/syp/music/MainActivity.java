package com.example.syp.music;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.所要实现功能：歌词歌曲同步
 * 2.利用 返回onResume()方法 和   实例化MediaPlayer  可实现 播放功能
 * 3.停止onPause()方法    利用AsyncTask异步 实现doInBackground()方法
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private boolean running;
    private LrcReader lrcReader;
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readLrc();
        player = MediaPlayer.create(this, R.raw.ssss);
        tv = (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void onResume() { //返回
        super.onResume();

        running = true;
        syncLrcTask.execute();
        player.start();
    }

    //-----------------上方所有可实现 播放功能,下方是实现 读取歌词功能---------------------------
    @Override
    protected void onPause() { //停止
        super.onPause();
        running = false;
        player.stop();
    }

    //异步处理
    private AsyncTask syncLrcTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            while (running) {
                try {
                    Thread.sleep(1000);
                    publishProgress(); //更新UI线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);

            String content = lrcReader.getContent(player.getCurrentPosition() / 1000);
            if (content != null) {
                tv.setText(content);
            }
//            Log.e("Time", (player.getCurrentPosition() / 1000) + "");
//            Log.e("Time",Math.round(((double)player.getCurrentPosition())/1000)+"");
        }
    };

    private void readLrc() {
        InputStream in = getResources().openRawResource(R.raw.ss);
        try {
            lrcReader = new LrcReader(in);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error parsing lrc", Toast.LENGTH_SHORT).show();
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

