package com.savchits.films.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
public class FilmsList {
    @SerializedName("list")
    private List<Film> filmsList;

    public List<Film> getFilmsList() {
        return filmsList;
    }

    public void setFilmsList(List<Film> filmsList) {
        this.filmsList = filmsList;
    }
}
