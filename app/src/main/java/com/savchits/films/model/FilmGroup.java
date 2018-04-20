package com.savchits.films.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Zakhar Savchits on 20.04.2018.
 */
public class FilmGroup extends ExpandableGroup<FilmDescription> {

    private long id;
    private String image;
    private String name;
    private String name_eng;
    private String premiere;
    private String description;


    public FilmGroup(List<FilmDescription> items, long id, String description, String image, String name, String name_eng, String premiere) {
        super(description, items);
        this.id = id;
        this.image = image;
        this.name = name;
        this.name_eng = name_eng;
        this.premiere = premiere;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
