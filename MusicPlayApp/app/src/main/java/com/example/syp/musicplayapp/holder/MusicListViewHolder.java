package com.example.syp.musicplayapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syp.musicplayapp.Manger.RecycleOnItemClickListenerManger;
import com.example.syp.musicplayapp.databinding.ItemMusciSongBinding;


/**
 * Created by syp on 17-5-26.
 */

public class MusicListViewHolder extends RecyclerView.ViewHolder {
    private ItemMusciSongBinding itemSongBinding;
    private TextView itemMusicSongName, itemMusicSongSinger;
    private LinearLayout itemLinearLayout;
    private RecycleOnItemClickListenerManger recycleOnItemClickListenerManger;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        itemSongBinding = ItemMusciSongBinding.bind(itemView);
        itemMusicSongName = itemSongBinding.itemMusicSongName;
        itemMusicSongSinger = itemSongBinding.itemMusicSongSinger;
        itemLinearLayout = itemSongBinding.itemLinearLayout;
        recycleOnItemClickListenerManger = RecycleOnItemClickListenerManger.getRecycleOnItemClickListenerManger();
    }

    public ItemMusciSongBinding getItemSongBinding() {
        return itemSongBinding;
    }

    public TextView getItemMusicSongName() {
        return itemMusicSongName;
    }

    public TextView getItemMusicSongSinger() {
        return itemMusicSongSinger;
    }

    public LinearLayout getItemLinearLayout() {
        return itemLinearLayout;
    }

    public RecycleOnItemClickListenerManger getRecycleOnItemClickListenerManger() {
        return recycleOnItemClickListenerManger;
    }
}
