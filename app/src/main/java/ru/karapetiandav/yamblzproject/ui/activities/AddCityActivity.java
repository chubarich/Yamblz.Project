package ru.karapetiandav.yamblzproject.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.fragments.AddCityFragment;

public class AddCityActivity extends AppCompatActivity {

    private AddCityFragment addCityFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        addCityFragment = (AddCityFragment) getFragmentManager()
                .findFragmentByTag(AddCityFragment.TAG);
        if (addCityFragment == null) {
            addCityFragment = new AddCityFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, addCityFragment, AddCityFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) destroyFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        destroyFragment();
    }

    private void destroyFragment() {
        getFragmentManager().beginTransaction().remove(addCityFragment).commit();
    }

}
