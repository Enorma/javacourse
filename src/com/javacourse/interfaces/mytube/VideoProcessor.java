package com.javacourse.interfaces.mytube;

public class VideoProcessor {

    //--------------------------------------------------------
    //fields

    private Encodeable encoder;
    private Storeable storage;
    private Notifiable notifier;

    //--------------------------------------------------------
    //constructors

    public VideoProcessor(Encodeable _encoder, Storeable _storage, Notifiable _notifier) {

        this.encoder  = _encoder;
        this.storage  = _storage;
        this.notifier = _notifier;
    }
    //VideoProcessor

    //--------------------------------------------------------
    //methods

    public void process(Video video, Encodeable _encoder, Storeable _storage, Notifiable _notifier) {

        //this uses the temporary dependencies provided at the arguments

        System.out.println("processing " + video.getFileName() + "...");

        _encoder.encode(video);
        _storage.store(video);
        _notifier.notify(video.getUser());

        System.out.println("processed " + video.getFileName() + " successfully!");
    }
    //process

    public void process(Video video) {
        //this uses the stored dependencies
        this.process(video, this.encoder, this.storage, this.notifier);
    }
    //process
}
//VideoProcessor

//eof
