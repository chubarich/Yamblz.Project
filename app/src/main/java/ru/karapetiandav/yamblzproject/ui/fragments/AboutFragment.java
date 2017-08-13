package ru.karapetiandav.yamblzproject.ui.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.callbacks.TitleCallback;

public class AboutFragment extends Fragment {

    private TitleCallback titleCallback;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();

        return fragment;
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
        titleCallback.setTitle(getString(R.string.navigation_about_text));
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
