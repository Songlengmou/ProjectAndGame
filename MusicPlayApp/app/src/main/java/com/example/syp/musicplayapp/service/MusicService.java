package com.example.syp.musicplayapp.service;

import android.app.Service;
import android.content.Intent;
import android.databinding.Bindable;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import com.example.syp.musicplayapp.Bean.Music;

import java.io.IOException;
import java.util.List;

/**
 * Created by syp on 17-5-26.
 */

public class MusicService extends Service {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends Binder {
        public MusicService getMusicService() {

            return MusicService.this;
        }


    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //从新播放
    public void replay(int po, List<Music> list) {

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(list.get(po).getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //播放下一首
    public int next(int po, List<Music> list) {
        if (po > list.size() - 1) {
            return 0;
        } else {
            return po;
//            需要调用从新播放的方法
//            replay(po,list);
        }
    }

    //判断是否在播放
    public boolean isplay() {
        return mediaPlayer.isPlaying();
    }

    //开始播放
    public void play() {
        mediaPlayer.start();
    }

    //停止播放
    public void stop() {
        mediaPlayer.stop();
    }

    //暂停播放
    public void pause() {
        mediaPlayer.pause();
    }

    public void xunhuan() {
        while (true) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
