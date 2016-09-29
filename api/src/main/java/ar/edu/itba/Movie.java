package ar.edu.itba;

import java.io.Serializable;

/**
 * Created by lumarzo on 28/09/16.
 */
public class Movie implements Serializable{
    private String name;
    private String genre;

    public Movie(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public String getGenre() {
        return genre;
    }
}
