package com.example.syp.musicplayapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syp.musicplayapp.R;
import com.example.syp.musicplayapp.databinding.FragmentMusicAlbumListBinding;
import com.example.syp.musicplayapp.presario.AlbumPresenter;


/**
 * Created by syp on 17-5-26.
 */

public class MusicAlbumLIstFragment extends Fragment {
    private View view;
    private FragmentMusicAlbumListBinding albumListBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.from(getActivity()).inflate(R.layout.fragment_music_album_list, container, false);
        albumListBinding = FragmentMusicAlbumListBinding.bind(view);
        AlbumPresenter albumPersenter = new AlbumPresenter(albumListBinding, getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
