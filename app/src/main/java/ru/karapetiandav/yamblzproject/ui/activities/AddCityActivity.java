package ru.karapetiandav.yamblzproject.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.App;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.di.modules.AddCityModule;
import ru.karapetiandav.yamblzproject.ui.adapters.AddCityAdapter;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenter;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;

public class AddCityActivity extends AppCompatActivity implements AddCityView {

    @Inject
    AddCityAdapter adapter;
    @Inject
    AddCityPresenter<AddCityView> presenter;

    @BindView(R.id.yandex_logo) ImageView yandexLogo;
    @BindView(R.id.input_city_edittext) EditText inputCityET;
    @BindView(R.id.cities_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.no_results_textview) TextView noResultsTV;
    @BindView(R.id.no_data_error_textview) TextView errorTV;
    @BindView(R.id.loading_progressbar) ProgressBar progressBar;
    @BindView(R.id.toolbar_add_city) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        App.getAppComponent().plusAddCityComponent(new AddCityModule()).inject(this);
        ButterKnife.bind(this);
        setupRecyclerView();
        setupToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        presenter.observeInputChanges(RxTextView.textChanges(inputCityET).skipInitialValue());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setOnCityClickListener(position -> presenter.onCityClick(position));
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showCities(List<CityViewModel> cities) {
        noResultsTV.setVisibility(View.GONE);
        errorTV.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        yandexLogo.setVisibility(View.GONE);
        adapter.changeDataSet(cities);
    }



    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void showNoMatches() {
        recyclerView.setVisibility(View.GONE);
        noResultsTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.GONE);
        noResultsTV.setVisibility(View.GONE);
        errorTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        noResultsTV.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void close() {
        finish();
    }
}
