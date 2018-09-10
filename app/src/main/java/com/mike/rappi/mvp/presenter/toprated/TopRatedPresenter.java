package com.mike.rappi.mvp.presenter.toprated;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.view.toprated.ITopRatedView;
import com.mike.rappi.util.Constants;
import io.realm.Realm;
import io.realm.RealmObject;
import java.util.Locale;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mike
 */

public class TopRatedPresenter implements ITopRatedPresenter {

  private ITopRatedView view;
  private ApiSource apiSource;
  private Realm realm;

  @Inject
  public TopRatedPresenter(ITopRatedView view, ApiSource apiSource) {
    this.view = view;
    this.apiSource = apiSource;
    realm = Realm.getDefaultInstance();
  }

  @Override
  public void loadTopRatedMovies() {
    view.showProgress();

    apiSource.getTopRatedMovies(Constants.API_KEY, Locale.getDefault().getLanguage())
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(topRatedResponse -> {
              view.hideProgress();
              view.showTopRatedMovies(topRatedResponse.getTopRatedResultsList());
              realm.executeTransactionAsync(
                  realm1 -> realm1.insertOrUpdate(topRatedResponse.getTopRatedResultsList()),
                  () -> Timber.e("onSucces"), Timber::e);
            },
            e -> Timber.e(e.getMessage()));
  }
}
