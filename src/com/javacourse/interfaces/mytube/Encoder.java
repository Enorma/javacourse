package com.javacourse.interfaces.mytube;

public class Encoder implements Encodeable {

    @Override
    public void encode(Video video) {

        System.out.println("encoding " + video.getFileName());
    }
    //encode
}
//Encoder

//eof
