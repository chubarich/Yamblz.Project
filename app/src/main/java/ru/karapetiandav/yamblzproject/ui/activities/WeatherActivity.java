package ru.karapetiandav.yamblzproject.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.App;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.di.modules.WeatherModule;
import ru.karapetiandav.yamblzproject.ui.adapters.WeatherAdapter;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenter;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    public static final String TAG = "weather_activity_tag";
    private static final int SPAN_COUNT_DEFAULT = 2;

    @Inject WeatherPresenter<WeatherView> weatherPresenter;
    @Inject WeatherAdapter weatherAdapter;

    @BindView(R.id.weather_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.weather_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar_weather) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        App.getAppComponent().plusWeatherComponent(new WeatherModule()).inject(this);
        weatherPresenter.onAttach(this);
        setUpLayout();
        setupToolbar();
        weatherPresenter.onCityRestored(getIntent().getExtras().getParcelable(TAG));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpLayout() {
        swipeRefreshLayout.setOnRefreshListener(weatherPresenter::onSwipeToRefresh);
        RecyclerView.LayoutManager layoutManager;
        if (getResources().getBoolean(R.bool.isTablet)) {
            layoutManager = new GridLayoutManager(this, SPAN_COUNT_DEFAULT);
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weatherAdapter);
    }

    @Override
    public void showWeather(List<WeatherViewModel> weatherList) {
        weatherAdapter.updateData(weatherList);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_no_data), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        weatherPresenter.onDetach();
    }
}
