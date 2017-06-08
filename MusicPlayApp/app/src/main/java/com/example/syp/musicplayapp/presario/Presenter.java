package com.example.syp.musicplayapp.presario;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.syp.musicplayapp.Manger.RecycleOnItemClickListenerManger;
import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.databinding.ActivityMainBinding;
import com.example.syp.musicplayapp.fragment.MusicAlbumLIstFragment;
import com.example.syp.musicplayapp.fragment.MusicSingerListFragment;
import com.example.syp.musicplayapp.fragment.MusicSongListFragment;
import com.example.syp.musicplayapp.interfaces.MusicOnClickName;
import com.example.syp.musicplayapp.service.MusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syp on 17-5-26.
 */

public class Presenter implements ViewPager.OnPageChangeListener, MusicOnClickName {
    private Context context;
    private ActivityMainBinding activityMainBinding;
    private FragmentManager fragmentManager;
    private MusicSongListFragment musicSongListFragment = new MusicSongListFragment();
    private MusicSingerListFragment musicSingerListFragment = new MusicSingerListFragment();
    private MusicAlbumLIstFragment musicAlbumListFragment = new MusicAlbumLIstFragment();
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private ViewPager mainMusicFragmentViewpager;
    private Button btnSong, btnSinger, btnAblum;
    private ImageButton imBtnStart, imBtnPause, imBtnNaxt;
    private MusicService musicService;
    private TextView textName;

    public Presenter(ActivityMainBinding activityMainBinding, Context context, FragmentManager fragmentManager) {
        this.activityMainBinding = activityMainBinding;
        this.context = context;
        this.fragmentManager = fragmentManager;

        mainMusicFragmentViewpager = activityMainBinding.mainMusicFragmentViewpager;
        btnAblum = activityMainBinding.mainMusicAlbum;
        btnSinger = activityMainBinding.mainMusicSinger;
        btnSong = activityMainBinding.mainMusicSong;
        textName = activityMainBinding.mainMusicName;
        imBtnStart = activityMainBinding.mainMusicStart;
        imBtnPause = activityMainBinding.mainMusicSuspend;
        imBtnNaxt = activityMainBinding.mainMusicNext;


        activityMainBinding.setPresenter(this);
        RecycleOnItemClickListenerManger.getRecycleOnItemClickListenerManger().setMusicOnClickName(this);
        addFragment();
        viewPagerFirst();
        context.startService(new Intent(context, MusicService.class));
        Intent in = new Intent(context, MusicService.class);
        context.bindService(in, getServiceConnection(), context.BIND_AUTO_CREATE);

    }

    public ActivityMainBinding getActivityMainBinding() {
        return activityMainBinding;
    }

    public void setActivityMainBinding(ActivityMainBinding activityMainBinding) {
        this.activityMainBinding = activityMainBinding;
    }

    public Context getContext() {
        return context;
    }


    public void btnSinger() {
        mainMusicFragmentViewpager.setCurrentItem(1);
        setBackgroud(mainMusicFragmentViewpager.getCurrentItem());


    }

    public void btnAlbum() {
        mainMusicFragmentViewpager.setCurrentItem(2);
        setBackgroud(mainMusicFragmentViewpager.getCurrentItem());
    }

    public void btnSong() {
        mainMusicFragmentViewpager.setCurrentItem(0);
        setBackgroud(mainMusicFragmentViewpager.getCurrentItem());
    }

    public void musicPlay() {
        if (musicService.isplay()) {
            musicService.pause();
            imBtnPause.setVisibility(View.GONE);
            imBtnStart.setVisibility(View.VISIBLE);
        } else {
            musicService.play();
            imBtnPause.setVisibility(View.VISIBLE);
            imBtnStart.setVisibility(View.GONE);
        }

    }

    public void musicPause() {
        if (musicService.isplay()) {
            musicService.pause();
            imBtnPause.setVisibility(View.GONE);
            imBtnStart.setVisibility(View.VISIBLE);
        } else {
            musicService.play();
            imBtnPause.setVisibility(View.VISIBLE);
            imBtnStart.setVisibility(View.GONE);
        }
    }


    public void musicPrevious(){

    }

    public void musicNext() {


    }

    public void viewPagerFirst() {
        fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {


                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        mainMusicFragmentViewpager.setAdapter(fragmentPagerAdapter);
        mainMusicFragmentViewpager.setCurrentItem(0);
        mainMusicFragmentViewpager.setOnPageChangeListener(this);
        setBackgroud(0);
    }

    public void addFragment() {
        list.add(musicSongListFragment);
        list.add(musicSingerListFragment);
        list.add(musicAlbumListFragment);
    }

    private void setBackgroud(int i) {
        if (i == 0) {
            btnSong.setBackgroundResource(R.color.colorPrimary);
            btnSinger.setBackgroundResource(R.color.colorAccent);
            btnAblum.setBackgroundResource(R.color.colorAccent);
        }
        if (i == 1) {
            btnSong.setBackgroundResource(R.color.colorAccent);
            btnSinger.setBackgroundResource(R.color.colorPrimary);
            btnAblum.setBackgroundResource(R.color.colorAccent);
        }
        if (i == 2) {
            btnSong.setBackgroundResource(R.color.colorAccent);
            btnSinger.setBackgroundResource(R.color.colorAccent);
            btnAblum.setBackgroundResource(R.color.colorPrimary);
        }
    }

    //-----------------------实现OnPageChangeListener------------------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBackgroud(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClickName(String name) {
        textName.setText(name);
    }

    public ServiceConnection getServiceConnection() {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicService.MyBind service1 = (MusicService.MyBind) service;
                if (service != null) {
                    musicService = service1.getMusicService();

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }
}
