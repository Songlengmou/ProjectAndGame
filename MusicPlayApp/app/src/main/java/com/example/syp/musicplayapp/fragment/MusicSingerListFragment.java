package com.example.syp.musicplayapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.databinding.FragmentMusicSingerListBinding;


/**
 * Created by s on 17-5-26.
 */

public class MusicSingerListFragment extends Fragment {
    private View view;
    private FragmentMusicSingerListBinding singerBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.from(getActivity()).inflate(R.layout.fragment_music_singer_list, container, false);
        singerBind = FragmentMusicSingerListBinding.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
