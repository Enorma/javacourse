package com.javacourse.streams;

public class Movie implements Comparable<Movie> {

    //---------------------------------------------------------------------------------
    //fields

    private String title;
    private int likes;
    private Genre genre;

    //---------------------------------------------------------------------------------
    //constructors

    public Movie(String _title, int _likes, Genre _genre) {

        this.title = _title;
        this.likes = _likes;
        this.genre = _genre;
    }
    //Movie

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public int compareTo(Movie other) {
        //Movies are compared by title by default
        return this.title.compareTo(other.title);
    }
    //compareTo

    @Override
    public String toString() {

        return this.title+":"+this.likes;
    }
    //compareTo

    //---------------------------------------------------------------------------------
    //getters

    public String getTitle() {

        return this.title;
    }
    //getTitle

    public int getLikes() {

        return this.likes;
    }
    //getLikes

    public Genre getGenre() {

        return this.genre;
    }
    //getGenre
}
//Movie

//eof
