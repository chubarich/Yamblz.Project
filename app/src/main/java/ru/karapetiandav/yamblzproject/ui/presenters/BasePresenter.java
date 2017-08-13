package ru.karapetiandav.yamblzproject.ui.presenters;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V> implements Presenter<V> {

    private V view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onAttach(V view) {
        if (view == null) {
            throw new IllegalArgumentException("View is null");
        }
        if (this.view != null) {
            throw new IllegalStateException("View already attached");
        }
        this.view = view;
    }

    @Override
    public void onDetach() {
        if (view == null) {
            throw new IllegalStateException("View already detached");
        }
        compositeDisposable.clear();
        view = null;
    }

    protected final void disposeOnDetach(Disposable d, Disposable... disposables) {
        compositeDisposable.add(d);
        compositeDisposable.addAll(disposables);
    }

    @Override
    public V getView() {
        return view;
    }
}
