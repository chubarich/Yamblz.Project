package ru.karapetiandav.yamblzproject.ui.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.App;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.di.modules.CitiesModule;
import ru.karapetiandav.yamblzproject.ui.activities.AddCityActivity;
import ru.karapetiandav.yamblzproject.ui.adapters.CitiesAdapter;
import ru.karapetiandav.yamblzproject.ui.callbacks.TitleCallback;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.presenters.CitiesPresenter;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;

public class CitiesFragment extends Fragment implements CitiesView {

    private static final int SPAN_COUNT_DEFAULT = 2;

    private OnCitySelected onCitySelected;
    private TitleCallback titleCallback;

    @BindView(R.id.cities_weather_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.no_city_added_textview) TextView noCitiesTV;
    @BindView(R.id.add_city_fab) FloatingActionButton addCityFAB;
    @BindView(R.id.cities_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    CitiesPresenter<CitiesView> presenter;
    @Inject
    CitiesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plusCitiesComponent(new CitiesModule()).inject(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        castContextToListener(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        castContextToListener(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        ButterKnife.bind(this, view);
        presenter.onAttach(this);
        setUpViews();
        titleCallback.setTitle(getString(R.string.navigation_cities_text));
        return view;
    }

    private void setUpViews() {
        setUpRecyclerView();
        addCityFAB.setOnClickListener(ignore -> presenter.onAddCityClick());
        swipeRefreshLayout.setOnRefreshListener(presenter::onSwipeToRefresh);
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager;
        if (getResources().getBoolean(R.bool.isTablet)) {
            layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT_DEFAULT);
        } else {
            layoutManager = new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnCityClickListener(presenter::onCityClick);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                presenter.onCityRemoved(adapter.getData().get(position).getCityViewModel());
                adapter.itemDismissed(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void showWeather(CityViewModel city) {
        onCitySelected.selectCity(city);
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

    public interface OnCitySelected {
        void selectCity(CityViewModel city);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
    }

    private void castContextToListener(Context context) {
        titleCallback = (TitleCallback) context;
        onCitySelected = (OnCitySelected) context;
    }
}
