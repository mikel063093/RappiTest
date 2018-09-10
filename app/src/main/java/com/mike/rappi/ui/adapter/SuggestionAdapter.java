package com.mike.rappi.ui.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import java.util.List;

public class SuggestionAdapter<T>  extends ArrayAdapter<T> {

  private List<T> items;
  private List<T> filteredItems;
  private ArrayFilter mFilter;

  public SuggestionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
    super(context, resource, objects);
    this.items = objects;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public T getItem(int position) {
    return items.get(position);
  }

  @NonNull @Override
  public Filter getFilter() {
    if (mFilter == null) {
      mFilter = new ArrayFilter();
    }
    return mFilter;
  }

  public int getCount() {
    //todo: change to pattern-size
    return items.size();
  }

  private class ArrayFilter extends Filter {
    @NonNull @Override
    protected FilterResults performFiltering(CharSequence prefix) {
      FilterResults results = new FilterResults();

      //custom-filtering of results
      results.values = items;
      results.count = items.size();

      return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, @NonNull FilterResults results) {
      filteredItems = (List<T>) results.values;
      if (results.count > 0) {
        notifyDataSetChanged();
      } else {
        notifyDataSetInvalidated();
      }
    }
  }
}