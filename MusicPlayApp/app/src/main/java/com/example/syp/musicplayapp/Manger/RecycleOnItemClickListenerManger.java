package com.example.syp.musicplayapp.Manger;


import com.example.syp.musicplayapp.interfaces.MusicOnClickName;
import com.example.syp.musicplayapp.interfaces.MusicOnService;
import com.example.syp.musicplayapp.interfaces.RecycleOnItemClickListener;

/**
 * Created by s on 17-5-26.
 */

public class RecycleOnItemClickListenerManger {
    private RecycleOnItemClickListener recycleOnItemClickListener;
    private MusicOnClickName musicOnClickName;
    private MusicOnService musicOnService;
    private static RecycleOnItemClickListenerManger recycleOnItemClickListenerManger = null;

    public static synchronized RecycleOnItemClickListenerManger getRecycleOnItemClickListenerManger() {

        if (recycleOnItemClickListenerManger == null) {
            recycleOnItemClickListenerManger = new RecycleOnItemClickListenerManger();
        }
        return recycleOnItemClickListenerManger;
    }

    public RecycleOnItemClickListener getRecycleOnItemClickListener() {
        return recycleOnItemClickListener;
    }

    public void setRecycleOnItemClickListener(RecycleOnItemClickListener recycleOnItemClickListener) {
        this.recycleOnItemClickListener = recycleOnItemClickListener;
    }

    public MusicOnClickName getMusicOnClickName() {
        return musicOnClickName;
    }

    public void setMusicOnClickName(MusicOnClickName musicOnClickName) {
        this.musicOnClickName = musicOnClickName;
    }

    public MusicOnService getMusicOnService() {
        return musicOnService;
    }

    public void setMusicOnService(MusicOnService musicOnService) {
        this.musicOnService = musicOnService;
    }
}
