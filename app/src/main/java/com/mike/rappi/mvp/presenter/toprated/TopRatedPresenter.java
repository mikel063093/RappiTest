package com.mike.rappi.mvp.presenter.toprated;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.model.entity.toprated.TopRatedResults;
import com.mike.rappi.mvp.view.toprated.ITopRatedView;
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

public class TopRatedPresenter implements ITopRatedPresenter {

  private ITopRatedView view;
  private ApiSource apiSource;
  private Realm realm;

  @Inject
  public TopRatedPresenter(ITopRatedView view, ApiSource apiSource) {
    this.view = view;
    this.apiSource = apiSource;
    this.realm = Realm.getDefaultInstance();
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
            e -> {
              RealmResults<TopRatedResults> result = realm.where(TopRatedResults.class).findAll();
              view.hideProgress();
              view.showTopRatedMovies(result);
              Timber.e(e);
            }, () -> view.hideProgress());
  }
}
