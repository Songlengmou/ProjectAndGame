package com.example.syp.musicplayapp.Bean;

/**
 * Created by syp on 17-5-26.
 */

public class Music {
    private String name;//歌曲名字
    private String artist;//歌手
    private String path;//歌曲路径
    private String duration;//歌曲时长
    private String album;//专辑

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
