package com.mike.rappi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.mike.rappi.R;
import com.mike.rappi.model.entity.popular.PopularResults;
import com.mike.rappi.util.RxBus;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<PopularResults> {

  private List<PopularResults> list;
  List<PopularResults> filteredList = new ArrayList<>();
  private Context context;

  public AutoCompleteAdapter(Context context, List<PopularResults> list, RxBus rxBus) {
    super(context, 0, list);
    this.list = list;
    this.context = context;
  }

  @Override
  public int getCount() {
    return filteredList.size();
  }

  @Override
  public Filter getFilter() {
    return new MoviesFilter(this, list);
  }

  @Override
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
    // Get the data item from filtered list.
    PopularResults item = list.get(position);

    // Inflate your custom row layout as usual.
    LayoutInflater inflater = LayoutInflater.from(getContext());
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.card_row, parent, false);
    }
    TextView tvTitle = convertView.findViewById(R.id.movieTitle);
    TextView tvYear = convertView.findViewById(R.id.movieYear);

    tvTitle.setText(item.getTitle());
    tvYear.setText(item.getRelease_date());

    return convertView;
  }
}
