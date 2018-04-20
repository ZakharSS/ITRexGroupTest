package com.savchits.films.net;

import com.savchits.films.model.FilmsList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

import static com.savchits.films.Constants.FILMS_URL;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
public interface ApiInterface {

    @GET(FILMS_URL)
    Single<Response<FilmsList>> getListOfFilms();
}
