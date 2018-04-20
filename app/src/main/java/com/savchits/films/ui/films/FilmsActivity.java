package com.savchits.films.ui.films;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.savchits.films.R;
import com.savchits.films.event.ErrorEvent;
import com.savchits.films.model.Film;
import com.savchits.films.model.FilmDescription;
import com.savchits.films.model.FilmGroup;
import com.savchits.films.viewModel.FilmsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class FilmsActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FilmsViewModel filmsViewModel;
    private LiveData<List<Film>> filmsLiveData;
    private LiveData<ErrorEvent> errorData;
    private RecyclerView mRvFilms;
    private SwipeRefreshLayout mRefreshLayout;
    private FilmsAdapter filmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        setToolbar();
        mRvFilms = findViewById(R.id.rvFilms);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(filmsRefreshListener);
        configureFilmsRecyclerView();
        filmsViewModel = ViewModelProviders.of(this, viewModelFactory).get(FilmsViewModel.class);
        filmsLiveData = filmsViewModel.getFilms();
        errorData = filmsViewModel.getErrorData();
        observeFilms();
        observeErrorAndLoadingEvents();
    }


    private void configureFilmsRecyclerView() {
        mRvFilms.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator animator = mRvFilms.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }

    private void observeFilms() {
        filmsLiveData.observe(this, films -> {
            if (films != null) {
                filmsAdapter = new FilmsAdapter(setGroups(films));
                mRvFilms.setAdapter(filmsAdapter);
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void observeErrorAndLoadingEvents() {
        errorData.observe(this, errorEvent -> {
            if (errorEvent != null) {
                if (errorEvent.getMessage() != null) {
                    Toast.makeText(this, errorEvent.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    SwipeRefreshLayout.OnRefreshListener filmsRefreshListener = () -> filmsViewModel.loadFilmsData();

    private List<FilmGroup> setGroups(List<Film> films) {

        List<FilmGroup> groups = new ArrayList<>();
        for (Film film : films) {
            List<FilmDescription> filmDescriptions = new ArrayList<>();
            filmDescriptions.add(new FilmDescription(film.getDescription()));
            FilmGroup filmGroup = new FilmGroup(
                    filmDescriptions,
                    film.getId(),
                    film.getDescription(),
                    film.getImage(),
                    film.getName(),
                    film.getName_eng(),
                    film.getPremiere()
            );
            groups.add(filmGroup);
        }
        return groups;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        filmsAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        filmsAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
