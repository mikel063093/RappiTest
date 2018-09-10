package com.mike.rappi.ui.adapter;

import android.widget.Filter;
import com.mike.rappi.model.entity.popular.PopularResults;
import java.util.ArrayList;
import java.util.List;

public class MoviesFilter extends Filter {
  private AutoCompleteAdapter adapter;
  private List<PopularResults> originalList;
  private List<PopularResults> filteredList;

  public MoviesFilter(AutoCompleteAdapter adapter, List<PopularResults> originalList) {
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
      for (final PopularResults item : originalList) {
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
    adapter.filteredList.addAll((List) results.values);
    adapter.notifyDataSetChanged();
  }
}
