package com.savchits.films.viewModel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.savchits.films.R;
import com.savchits.films.event.ErrorEvent;
import com.savchits.films.model.Film;
import com.savchits.films.model.FilmsList;
import com.savchits.films.net.RetrofitService;
import com.savchits.films.room.FilmsDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Zakhar Savchits on 18.04.2018.
 */
public class FilmsViewModel extends ViewModel {

    private final String TAG = FilmsViewModel.class.getSimpleName();

    private final RetrofitService retrofitService;
    private final FilmsDao filmsDao;
    private MutableLiveData<List<Film>> filmsData;
    private MutableLiveData<ErrorEvent> errorData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable;

    @Inject
    public FilmsViewModel(RetrofitService retrofitService, FilmsDao filmsDao) {
        this.retrofitService = retrofitService;
        this.filmsDao = filmsDao;
    }

    public LiveData<List<Film>> getFilms() {
        if (filmsData == null) {
            filmsData = new MutableLiveData<>();
            compositeDisposable = new CompositeDisposable();
            loadFilmsData();
        } else {
            getLastFilmsFromDB();
            errorData.postValue(new ErrorEvent(R.string.error_obtaining_films, false));
        }
        return filmsData;
    }

    public MutableLiveData<ErrorEvent> getErrorData() {
        return errorData;
    }

    public void loadFilmsData() {
        errorData.postValue(new ErrorEvent(null, true));
        compositeDisposable.add(retrofitService.getListOfFilms().subscribe(this::processFilmsListResponse, this::processFilmsError));
    }

    private void processFilmsListResponse(@NonNull Response<FilmsList> filmsList) {
        if (filmsList.body() != null) {
            List<Film> films = filmsList.body().getFilmsList();
            if (films != null && !films.isEmpty()) {
                for (int i = 0; i < films.size(); i++) {//bad approach because of poor API without unique ids :)
                    films.get(i).setId(i);
                }
                saveFilmsToDB(films);
                filmsData.postValue(films);
            } else {
                getLastFilmsFromDB();
            }
        }
        errorData.postValue(new ErrorEvent(null, false));
    }

    private void processFilmsError(Throwable throwable) {
        getLastFilmsFromDB();
        throwable.printStackTrace();
    }

    private void getLastFilmsFromDB() {
        compositeDisposable.add(filmsDao.getAllFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFilmsReceived, this::processFilmsReceivedError));
    }

    private void onFilmsReceived(List<Film> films) {
        filmsData.postValue(films);
        errorData.postValue(new ErrorEvent(R.string.error_obtaining_films, false));
    }

    private void processFilmsReceivedError(Throwable throwable) {
        Log.d(TAG, "Error obtaining films from DB" + throwable.getMessage());
        throwable.printStackTrace();
        errorData.postValue(new ErrorEvent(null, false));
    }

    @SuppressLint("CheckResult")
    private void saveFilmsToDB(List<Film> films) {
        Completable.fromAction(() -> filmsDao.insert(films))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processFilmsSaveResponse, this::processFilmsSaveError);
    }

    private void processFilmsSaveResponse() {
        Log.d(TAG, "Films saved");
    }

    private void processFilmsSaveError(Throwable throwable) {
        Log.d(TAG, "Error. Films were not saved." + throwable.getMessage());
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
    }
}
