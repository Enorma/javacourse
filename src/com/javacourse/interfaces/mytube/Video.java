package com.javacourse.interfaces.mytube;

public class Video {

    //--------------------------------------------------------
    //fields

    private String fileName;
    private String title;
    private User user;

    //--------------------------------------------------------
    //constructors

    public Video(User _user, String _title, String _filename) {

        this.user = _user;
        this.title = _title;
        this.fileName = _filename;
    }
    //Video

    //--------------------------------------------------------
    //getters

    public String getFileName() {

        return this.fileName;
    }
    //getFileName

    public String getTitle() {

        return this.title;
    }
    //getTitle

    public User getUser() {

        return this.user;
    }
    //getUser

    //--------------------------------------------------------
    //setters

    public void setFileName(String _filename) {

        this.fileName = _filename;
    }
    //setFileName

    public void setTitle(String _title) {

        this.title = _title;
    }
    //setTitle

    public void setUser(User _user) {

        this.user = _user;
    }
    //setUser
}
//Video

//eof
