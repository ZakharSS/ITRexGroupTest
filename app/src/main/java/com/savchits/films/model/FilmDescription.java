package com.savchits.films.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zakhar Savchits on 20.04.2018.
 */
public class FilmDescription implements Parcelable {

    private String description;

    public FilmDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected FilmDescription(Parcel in) {
        description = in.readString();
    }

    public static final Creator<FilmDescription> CREATOR = new Creator<FilmDescription>() {
        @Override
        public FilmDescription createFromParcel(Parcel in) {
            return new FilmDescription(in);
        }

        @Override
        public FilmDescription[] newArray(int size) {
            return new FilmDescription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
    }
}
