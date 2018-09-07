package com.mike.rappi.mvp.presenter.popular;



import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.view.popular.IPopularView;
import com.mike.rappi.util.Constants;
import java.util.Locale;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mike
 */

public class PopularPresenter implements IPopularPresenter {

    private IPopularView view;
    private ApiSource apiSource;

    @Inject
    public PopularPresenter(IPopularView view, ApiSource apiSource) {
        this.view = view;
        this.apiSource = apiSource;
    }

    @Override
    public void loadPopularMovies() {
        view.showProgress();

        apiSource.getPopularMovies(Constants.API_KEY, Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularResponse -> {
                            view.hideProgress();
                            view.showPopularMovies(popularResponse.getPopularResultsList());
                        },
                        e -> Timber.e(e.getMessage()));
    }
}
