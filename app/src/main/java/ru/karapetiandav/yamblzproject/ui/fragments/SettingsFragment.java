package ru.karapetiandav.yamblzproject.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import ru.karapetiandav.yamblzproject.R;

public class SettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateSummary();
    }

    private void updateSummary() {

    }
}
