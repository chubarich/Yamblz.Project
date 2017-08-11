package ru.karapetiandav.yamblzproject.data.prefs;


import android.content.SharedPreferences;
import android.content.res.Resources;

public class PreferenceHelperImpl implements PreferenceHelper {

    private SharedPreferences sharedPrefs;
    private Resources resources;

    public PreferenceHelperImpl(SharedPreferences sharedPrefs, Resources resources) {
        this.sharedPrefs = sharedPrefs;
        this.resources = resources;
    }
}
