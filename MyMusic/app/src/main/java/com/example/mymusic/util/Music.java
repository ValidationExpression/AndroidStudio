package com.example.mymusic.util;

public class Music {
    public String name;

    public String artist;

    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String path;
    public long duration;

    public Music() {
    }

    public Music(String name, String path,String artist, long duration) {
        this.name = name;
        this.path = path;
        this.artist=artist;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String toString(){
        return "Music{"+
                ", name='"+name+'\''+
                ", path='"+path+'\''+
                ", time='"+duration+
                '}';
    }
}
