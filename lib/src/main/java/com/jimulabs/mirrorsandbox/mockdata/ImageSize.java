package com.jimulabs.mirrorsandbox.mockdata;

/**
 * Created by lintonye on 15-03-04.
 */
public enum ImageSize {
    small(360),
    large(1080),
    hd(1980);
    final int width;

    ImageSize(int width) {
        this.width = width;
    }
}
