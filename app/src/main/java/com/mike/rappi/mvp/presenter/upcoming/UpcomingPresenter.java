package com.mike.rappi.mvp.presenter.upcoming;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.model.entity.upcoming.UpcomingResults;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
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

public class UpcomingPresenter implements IUpcomingPresenter {

  private IUpcomingView view;
  private ApiSource apiSource;
  private Realm realm;

  @Inject
  public UpcomingPresenter(IUpcomingView view, ApiSource apiSource) {
    this.view = view;
    this.apiSource = apiSource;
    this.realm = Realm.getDefaultInstance();
  }

  @Override
  public void loadUpcomingMovies() {
    view.showProgress();

    apiSource.getUpcomingMovies(Constants.API_KEY, Locale.getDefault().getLanguage())
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(topRatedResponse -> {
              view.hideProgress();
              view.showUpComingMovies(topRatedResponse.getTopRatedResultsList());
              realm.executeTransactionAsync(
                  realm1 -> realm1.insertOrUpdate(topRatedResponse.getTopRatedResultsList()),
                  () -> Timber.e("onSucces"), Timber::e);
            },
            e -> {
              RealmResults<UpcomingResults> result = realm.where(UpcomingResults.class).findAll();
              view.hideProgress();
              view.showUpComingMovies(result);
              Timber.e(e);
            }, () -> view.hideProgress());
  }
}
