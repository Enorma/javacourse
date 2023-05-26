package com.javacourse.interfaces.mytube;

public class Storage implements Storeable {

    @Override
    public void store(Video video) {

        System.out.println("storing " + video.getFileName());
    }
    //store
}
//Storage

//eof
