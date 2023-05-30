package com.example.sing;

public class Music {
    public String name;
    public String path;
    public long duration;

    public Music() {
    }

    public Music(String name, String path, long duration) {
        this.name = name;
        this.path = path;
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

    public String toString(){
        return "Music{"+
                ", name='"+name+'\''+
                ", path='"+path+'\''+
                ", time='"+duration+
                '}';
    }
}
