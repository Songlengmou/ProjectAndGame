package com.example.syp.musicplayapp.presario;

import android.content.Context;

import com.example.syp.musicplayapp.databinding.FragmentMusicAlbumListBinding;


/**
 * Created by syp on 17-5-26.
 */

public class AlbumPresenter {
    private FragmentMusicAlbumListBinding albumListBinding;
    private Context context;

    public AlbumPresenter(FragmentMusicAlbumListBinding albumListBinding, Context context) {
        this.albumListBinding = albumListBinding;
        this.context = context;
        albumListBinding.setAlbumPresenter(this);
    }

    public FragmentMusicAlbumListBinding getAlbumListBinding() {
        return albumListBinding;
    }

    public Context getContext() {
        return context;
    }
}
