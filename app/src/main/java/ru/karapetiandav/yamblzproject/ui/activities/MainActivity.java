package ru.karapetiandav.yamblzproject.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.callbacks.TitleCallback;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.fragments.AboutFragment;
import ru.karapetiandav.yamblzproject.ui.fragments.CitiesFragment;
import ru.karapetiandav.yamblzproject.ui.fragments.SettingsFragment;
import ru.karapetiandav.yamblzproject.ui.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CitiesFragment.OnCitySelected,
        TitleCallback {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar_add_city)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private FragmentManager fragmentManager = getFragmentManager();
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                toggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);// show back button
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            } else {
                //show hamburger
                toggle.setDrawerIndicatorEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toggle.syncState();
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);
                    }
                });
            }
        });


        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_cities);
            setTitle(navigationView.getMenu().findItem(R.id.nav_cities).getTitle());
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new CitiesFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_cities :
                fragmentClass = CitiesFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.nav_about:
                fragmentClass = AboutFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            Log.e(TAG, "onNavigationItemSelected: ", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "onNavigationItemSelected: ", e);
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        item.setChecked(true);
        setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void selectCity(CityViewModel city) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, WeatherFragment.newInstance(city))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}