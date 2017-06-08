package com.example.syp.musicplayapp.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.databinding.FragmentMusicSongListBinding;
import com.example.syp.musicplayapp.presario.SongPresenter;
import com.example.syp.musicplayapp.service.MusicService;

/**
 * Created by syp on 17-5-26.
 */

public class MusicSongListFragment extends Fragment {
    private View view;
    private SongPresenter songPersenter;
    private FragmentMusicSongListBinding viewDataBinding;
    private MusicService musicService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.from(getActivity()).inflate(R.layout.fragment_music_song_list, container, false);

        viewDataBinding = FragmentMusicSongListBinding.bind(view);
        songPersenter = new SongPresenter(viewDataBinding, getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
