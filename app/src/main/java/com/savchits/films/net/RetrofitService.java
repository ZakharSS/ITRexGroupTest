package com.savchits.films.net;

import com.savchits.films.model.FilmsList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
public class RetrofitService {

    private ApiInterface apiInterface;

    @Inject
    public RetrofitService(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<Response<FilmsList>> getListOfFilms() {
        return apiInterface.getListOfFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
