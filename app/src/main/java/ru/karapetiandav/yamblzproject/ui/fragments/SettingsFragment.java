package ru.karapetiandav.yamblzproject.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.callbacks.TitleCallback;

public class SettingsFragment extends PreferenceFragment {

    private TitleCallback titleCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        titleCallback.setTitle(getString(R.string.navigation_settings_text));

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        titleCallback = (TitleCallback) activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateSummary();
    }

    private void updateSummary() {

    }
}
