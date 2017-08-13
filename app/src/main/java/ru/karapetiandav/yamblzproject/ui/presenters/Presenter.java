package ru.karapetiandav.yamblzproject.ui.presenters;


public interface Presenter<V> {
    void onAttach(V view);
    void onDetach();
    V getView();
}
