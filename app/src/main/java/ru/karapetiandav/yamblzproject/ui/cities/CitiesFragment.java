package ru.karapetiandav.yamblzproject.ui.cities;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.App;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.di.module.CitiesModule;
import ru.karapetiandav.yamblzproject.ui.addcity.AddCityActivity;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public class CitiesFragment extends Fragment implements CitiesView {

    @BindView(R.id.cities_weather_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.no_city_added_textview) TextView noCitiesTV;
    @BindView(R.id.add_city_fab) FloatingActionButton addCityFAB;
    @BindView(R.id.cities_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

    @Inject CitiesPresenter<CitiesView> presenter;
    @Inject CitiesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plusCitiesComponent(new CitiesModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        ButterKnife.bind(this, view);
        presenter.onAttach(this);
        setUpViews();
        return view;
    }

    private void setUpViews() {
        setUpRecyclerView();
        addCityFAB.setOnClickListener(ignore -> presenter.onAddCityClick());
        swipeRefreshLayout.setOnRefreshListener(presenter::onSwipeToRefresh);
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnCityWeatherClickListener(presenter::onCityWeatherClick);
    }

    @Override
    public void showCity(CityWeatherViewModel city) {
        adapter.addCity(city);
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
    public void showNoCities(boolean show) {
        noCitiesTV.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(boolean show) {

    }

    @Override
    public void showAddNewCity() {
        startActivity(new Intent(getActivity(), AddCityActivity.class));
    }
}
