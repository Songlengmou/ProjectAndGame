package com.example.syp.musicplayapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.musicplayapp.Bean.Music;
import com.example.syp.musicplayapp.Manger.RecycleOnItemClickListenerManger;
import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.holder.MusicListViewHolder;
import com.example.syp.musicplayapp.interfaces.MusicOnClickName;
import com.example.syp.musicplayapp.interfaces.RecycleOnItemClickListener;

import java.util.List;

/**
 * Created by syp on 17-5-26.
 */

public class SongListAdapter extends Adapter {
    private List<Music> list;
    private Context context;

    public SongListAdapter(List<Music> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class SongViewHolders extends RecyclerView.ViewHolder {

        public SongViewHolders(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_musci_song, parent, false);
        MusicListViewHolder musicListViewHolder = new MusicListViewHolder(inflate);

        return musicListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Music music = list.get(position);
        final String name = music.getName();
        String artist = music.getArtist();
        MusicListViewHolder songHolder = (MusicListViewHolder) holder;
        songHolder.getItemMusicSongName().setText(name);
        songHolder.getItemMusicSongSinger().setText(artist);
//       songHolder.getRecycleOnItemClickListenerManger()
//                .getMusicOnService().onService(position,list);
        songHolder.getItemLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleOnItemClickListenerManger recycleOnItemClickListenerManger = RecycleOnItemClickListenerManger.getRecycleOnItemClickListenerManger();
                RecycleOnItemClickListener recycleOnItemClickListener = recycleOnItemClickListenerManger.getRecycleOnItemClickListener();
                recycleOnItemClickListener.viewOnItemClickListenre(position);
                MusicOnClickName musicOnClickName = recycleOnItemClickListenerManger.getMusicOnClickName();
                musicOnClickName.onClickName(name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
