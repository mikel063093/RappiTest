package com.mike.rappi.ui.adapter;

import android.widget.Filter;
import com.mike.rappi.model.entity.search.Movie;
import java.util.ArrayList;
import java.util.List;

public class MoviesFilter extends Filter {
  private AutoCompleteAdapter adapter;
  private List<Movie> originalList;
  private List<Movie> filteredList;

  public MoviesFilter(AutoCompleteAdapter adapter, List<Movie> originalList) {
    super();
    this.adapter = adapter;
    this.originalList = originalList;
    this.filteredList = new ArrayList<>();
  }

  @Override
  protected FilterResults performFiltering(CharSequence constraint) {
    filteredList.clear();
    final FilterResults results = new FilterResults();

    if (constraint == null || constraint.length() == 0) {
      filteredList.addAll(originalList);
    } else {
      final String filterPattern = constraint.toString().toLowerCase().trim();

      // Your filtering logic goes in here
      for (final Movie item : originalList) {
        if (item.getTitle().toLowerCase().contains(filterPattern)) {
          filteredList.add(item);
        }
      }
    }
    results.values = filteredList;
    results.count = filteredList.size();
    return results;
  }

  @Override
  protected void publishResults(CharSequence constraint, FilterResults results) {
    adapter.filteredList.clear();
    if (results != null && results.values != null) {
      adapter.filteredList.addAll((List) results.values);
    }
    adapter.notifyDataSetChanged();
  }
}
