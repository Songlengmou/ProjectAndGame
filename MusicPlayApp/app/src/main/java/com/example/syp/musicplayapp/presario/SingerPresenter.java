package com.example.syp.musicplayapp.presario;

import android.content.Context;

import com.example.syp.musicplayapp.databinding.FragmentMusicSingerListBinding;


/**
 * Created by syp on 17-5-26.
 */

public class SingerPresenter {
    private FragmentMusicSingerListBinding singerListBinding;
    private Context context;

    public SingerPresenter(FragmentMusicSingerListBinding singerListBinding, Context context) {
        this.singerListBinding = singerListBinding;
        this.context = context;
        singerListBinding.setSingerPersenter(this);
    }

    public FragmentMusicSingerListBinding getSingerListBinding() {
        return singerListBinding;
    }

    public Context getContext() {
        return context;
    }
}
