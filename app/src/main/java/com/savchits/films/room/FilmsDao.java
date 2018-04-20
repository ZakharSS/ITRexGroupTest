package com.savchits.films.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.savchits.films.model.Film;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Dao
public interface FilmsDao {
    @Query("SELECT * FROM Film")
    Single<List<Film>> getAllFilms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Film> films);
}
