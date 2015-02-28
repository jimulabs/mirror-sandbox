package com.jimulabs.googlemusicmock;

/**
 * Created by lintonye on 15-02-16.
 */
public class Album {
    public final String albumArtUrl;
    public final String artist;
    public final String title;
    public final String description;

    public Album(String albumArtUrl, String artist, String title, String description) {
        this.albumArtUrl = albumArtUrl;
        this.artist = artist;
        this.title = title;
        this.description = description;
    }
}
