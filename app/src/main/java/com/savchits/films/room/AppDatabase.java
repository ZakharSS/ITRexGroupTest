package com.savchits.films.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.savchits.films.model.Film;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Database(entities = {Film.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmsDao filmsDao();
}
