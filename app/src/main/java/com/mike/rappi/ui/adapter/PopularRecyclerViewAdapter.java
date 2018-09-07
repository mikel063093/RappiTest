package com.mike.rappi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mike.rappi.R;

import com.mike.rappi.event.PopularDetailEvent;
import com.mike.rappi.model.entity.popular.PopularResults;
import com.mike.rappi.ui.activity.MovieDetailActivity;
import com.mike.rappi.util.Constants;
import com.mike.rappi.util.RxBus;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mike
 */

public class PopularRecyclerViewAdapter extends RecyclerView.Adapter<PopularRecyclerViewAdapter.ViewHolder> {

    private List<PopularResults> resultsList;
    private Context context;
    private RxBus bus;

    @Inject
    public PopularRecyclerViewAdapter(List<PopularResults> resultsList, Context context, RxBus bus) {
        this.resultsList = resultsList;
        this.context = context;
        this.bus = bus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PopularResults results = resultsList.get(position);

        holder.title.setText(results.getTitle());
        holder.year.setText(results.getRelease_date());

        Glide.with(context)
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W185 + results.getPoster_path())
                .placeholder(R.drawable.movieicon)
                .into(holder.poster);

        holder.poster.setOnClickListener(v -> {
            bus.postSticky(new PopularDetailEvent(results));
            context.startActivity(new Intent(context, MovieDetailActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
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
