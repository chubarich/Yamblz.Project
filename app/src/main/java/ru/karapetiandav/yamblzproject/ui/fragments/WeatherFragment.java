package ru.karapetiandav.yamblzproject.ui.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.App;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.di.modules.WeatherModule;
import ru.karapetiandav.yamblzproject.ui.adapters.WeatherAdapter;
import ru.karapetiandav.yamblzproject.ui.callbacks.TitleCallback;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenter;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;

public class WeatherFragment extends Fragment implements WeatherView {

    private static final String CITY_KEY = "city_key";
    private static final int SPAN_COUNT_DEFAULT = 2;

    private TitleCallback titleCallback;

    @Inject WeatherPresenter<WeatherView> weatherPresenter;
    @Inject WeatherAdapter weatherAdapter;

    @BindView(R.id.weather_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.weather_recycler_view) RecyclerView recyclerView;

    public static WeatherFragment newInstance(CityViewModel city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(CITY_KEY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plusWeatherComponent(new WeatherModule()).inject(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        titleCallback = (TitleCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        setUpLayout();
        weatherPresenter.onAttach(this);
        CityViewModel city =  getArguments().getParcelable(CITY_KEY);
        weatherPresenter.onCityRestored(city);
        return view;
    }

    private void setUpLayout() {
        swipeRefreshLayout.setOnRefreshListener(weatherPresenter::onSwipeToRefresh);
        RecyclerView.LayoutManager layoutManager;
        if (getResources().getBoolean(R.bool.isTablet)) {
            layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT_DEFAULT);
        } else {
            layoutManager = new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.VERTICAL, false);
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
        Toast.makeText(getActivity(), getString(R.string.error_no_data), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTitle(String title) {
        titleCallback.setTitle(title);
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