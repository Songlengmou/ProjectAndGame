package com.example.syp.musicplayapp.presario;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.syp.musicplayapp.Bean.Music;
import com.example.syp.musicplayapp.Manger.RecycleOnItemClickListenerManger;
import com.example.syp.musicplayapp.adapter.SongListAdapter;
import com.example.syp.musicplayapp.databinding.FragmentMusicSongListBinding;
import com.example.syp.musicplayapp.interfaces.RecycleOnItemClickListener;
import com.example.syp.musicplayapp.service.MusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syp on 17-5-26.
 */

public class SongPresenter implements RecycleOnItemClickListener {

    private FragmentMusicSongListBinding songListBinding;
    private Context context;
    private RecyclerView musicListFragmentRecyclerView;
    private List<Music> list = new ArrayList<>();
    private MusicService.MyBind serviceBind;
    private MusicService musicService;


    public SongPresenter(FragmentMusicSongListBinding songListBinding, Context context) {
        this.songListBinding = songListBinding;
        this.context = context;
        musicListFragmentRecyclerView = songListBinding.musicListFragmentRecyclerView;

        list = getMusic(context, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        musicListFragmentRecyclerView.setLayoutManager(linearLayoutManager);
        musicListFragmentRecyclerView.setAdapter(new SongListAdapter(list, context));
        RecycleOnItemClickListenerManger.getRecycleOnItemClickListenerManger().setRecycleOnItemClickListener(this);
        Intent in = new Intent(context, MusicService.class);
        context.bindService(in, getServiceConnection(), context.BIND_AUTO_CREATE);
        songListBinding.setSongPersenter(this);


    }

    public FragmentMusicSongListBinding getSongListBinding() {
        return songListBinding;
    }

    public void setSongListBinding(FragmentMusicSongListBinding songListBinding) {
        this.songListBinding = songListBinding;
    }

    public Context getContext() {
        return context;
    }


    public List<Music> getMusic(Context context, List<Music> list) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] musicStr = new String[]{MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM
        };
        String selection = musicStr[2] + " > 10*1000";
        String so = musicStr[0] + " asc";
        Cursor query = contentResolver.query(uri, musicStr, selection, null, so);
        while (query.moveToNext()) {
            Music music = new Music();
            music.setName(query.getString(query.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            music.setAlbum(query.getString(query.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
            music.setArtist(query.getString(query.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            music.setDuration(query.getString(query.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            music.setPath(query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATA)));


            list.add(music);
        }
        query.close();
        return list;
    }

    @Override
    public void viewOnItemClickListenre(int item) {

        musicService.replay(item, list);

    }

    public ServiceConnection getServiceConnection() {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBind = (MusicService.MyBind) service;
                if (service != null) {
                    musicService = serviceBind.getMusicService();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }


}
