package com.mike.rappi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mike.rappi.R;
import com.mike.rappi.event.SearchDetailEvent;
import com.mike.rappi.model.entity.search.Movie;
import com.mike.rappi.model.entity.search.MoviesResponse;
import com.mike.rappi.ui.activity.MovieDetailActivity;
import com.mike.rappi.util.Constants;
import com.mike.rappi.util.RxBus;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

  private MoviesResponse moviesResponse;
  private Context context;
  private RxBus bus;

  public SearchAdapter(MoviesResponse moviesResponse, Context context, RxBus bus) {
    this.moviesResponse = moviesResponse;
    this.context = context;
    this.bus = bus;
  }

  @Override
  public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_movies, parent, false);

    return new SearchAdapter.ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Movie results = moviesResponse.getResults().get(position);

    holder.title.setText(results.getTitle());
    holder.year.setText(results.getReleaseDate());

    Glide.with(context)
        .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W185 + results.getBackdropPath())

        .into(holder.poster);

    holder.poster.setOnClickListener(v -> {
      bus.postSticky(new SearchDetailEvent(results));
      context.startActivity(new Intent(context, MovieDetailActivity.class));
    });
  }

  @Override public int getItemCount() {
    return moviesResponse.getResults().size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.moviePoster)
    ImageView poster;
    @BindView(R.id.movieTitle)
    TextView title;
    @BindView(R.id.movieYear)
    TextView year;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
