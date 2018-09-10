package com.mike.rappi.mvp.presenter.popular;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.model.entity.popular.PopularResults;
import com.mike.rappi.mvp.view.popular.IPopularView;
import com.mike.rappi.util.Constants;
import io.realm.Realm;
import io.realm.RealmResults;
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
  private Realm realm;

  @Inject
  public PopularPresenter(IPopularView view, ApiSource apiSource) {
    this.view = view;
    this.apiSource = apiSource;
    this.realm = Realm.getDefaultInstance();
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
          realm.executeTransactionAsync(
              realm1 -> realm1.insertOrUpdate(popularResponse.getPopularResultsList()),
              () -> Timber.e("onSucces"), Timber::e);
        }, throwable -> {
          RealmResults<PopularResults> result = realm.where(PopularResults.class).findAll();
          view.hideProgress();
          view.showPopularMovies(result);
          Timber.e(throwable);
        }, () -> view.hideProgress());
  }
}
