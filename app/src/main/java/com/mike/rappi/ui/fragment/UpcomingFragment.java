package com.mike.rappi.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mike.rappi.MoviesApp;
import com.mike.rappi.R;
import com.mike.rappi.di.upcoming.DaggerUpcomingComponet;
import com.mike.rappi.di.upcoming.UpcomingModule;
import com.mike.rappi.model.entity.upcoming.UpcomingResults;
import com.mike.rappi.mvp.presenter.upcoming.UpcomingPresenter;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
import com.mike.rappi.ui.adapter.UpcomingRecyclerViewAdapter;
import com.mike.rappi.util.RxBus;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements IUpcomingView {

  @BindView(R.id.upcomingProgress) ProgressBar upcomingProgress;
  @BindView(R.id.upcoming_movies_recyclerView) RecyclerView recyclerView;

  @Inject UpcomingPresenter presenter;
  @Inject RxBus bus;

  private UpcomingRecyclerViewAdapter adapter;

  public UpcomingFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
    ButterKnife.bind(this, view);

    initInjector();

    presenter.loadUpcomingMovies();

    return view;
  }

  @Override
  public void showProgress() {
    upcomingProgress.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
  }

  @Override
  public void hideProgress() {
    upcomingProgress.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override public void showUpComingMovies(List<UpcomingResults> topRatedResultsList) {
    adapter = new UpcomingRecyclerViewAdapter(topRatedResultsList, getContext(), bus);
    adapter.notifyDataSetChanged();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
  }

  private void initInjector() {
    DaggerUpcomingComponet.builder()
        .appComponent(MoviesApp.get(Objects.requireNonNull(getContext())).getAppComponent())
        .upcomingModule(new UpcomingModule(this))
        .build().inject(this);
  }
}
