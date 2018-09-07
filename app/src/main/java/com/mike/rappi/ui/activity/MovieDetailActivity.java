package com.mike.rappi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mike.rappi.MoviesApp;
import com.mike.rappi.R;
import com.mike.rappi.di.detail.DaggerDetailComponent;
import com.mike.rappi.event.PopularDetailEvent;
import com.mike.rappi.event.TopRatedDetailEvent;
import com.mike.rappi.util.Constants;
import com.mike.rappi.util.RxBus;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MovieDetailActivity extends AppCompatActivity {

  @Inject
  RxBus bus;

  @BindView(R.id.coverImage)
  ImageView coverImage;
  @BindView(R.id.detail_title)
  TextView detailTitle;
  @BindView(R.id.detail_backdrop)
  ImageView detailBackdrop;
  @BindView(R.id.release_date)
  TextView releaseDate;
  @BindView(R.id.detail_overview)
  TextView detailOverview;

  private CompositeSubscription mSubscription = new CompositeSubscription();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    ButterKnife.bind(this);

    // for transparent status bar
    getWindow().getDecorView()
        .setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    initInjector();

    mSubscription.add(bus.toStickyObservable()
        .subscribe(event -> {
              if (event instanceof PopularDetailEvent) {
                detailTitle.setText(((PopularDetailEvent) event).getResults().getTitle());
                releaseDate.setText(((PopularDetailEvent) event).getResults().getRelease_date());
                detailOverview.setText(((PopularDetailEvent) event).getResults().getOverview());

                Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL
                        + Constants.IMAGE_W92
                        + ((PopularDetailEvent) event).getResults().getPoster_path())
                    .placeholder(R.drawable.movieicon)
                    .into(detailBackdrop);

                Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL
                        + Constants.IMAGE_W780
                        + ((PopularDetailEvent) event).getResults().getBackdrop_path())
                    .into(coverImage);
              } else if (event instanceof TopRatedDetailEvent) {
                detailTitle.setText(((TopRatedDetailEvent) event).getResults().getTitle());
                releaseDate.setText(((TopRatedDetailEvent) event).getResults().getRelease_date());
                detailOverview.setText(((TopRatedDetailEvent) event).getResults().getOverview());

                Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL
                        + Constants.IMAGE_W342
                        + ((TopRatedDetailEvent) event).getResults().getPoster_path())
                    .placeholder(R.drawable.movieicon)
                    .into(detailBackdrop);

                Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL
                        + Constants.IMAGE_W780
                        + ((TopRatedDetailEvent) event).getResults().getBackdrop_path())
                    .into(coverImage);
              }
            },
            e -> Timber.e(e.getMessage())));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mSubscription != null && mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
  }

  private void initInjector() {
    DaggerDetailComponent.builder()
        .appComponent(MoviesApp.get(this).getAppComponent())
        .build().inject(this);
  }
}
