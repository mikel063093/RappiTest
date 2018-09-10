package com.mike.rappi.event;

import com.mike.rappi.model.entity.popular.PopularResults;

/**
 * Created by mike
 */

public class SearchDetailEvent {
  private PopularResults results;

  public SearchDetailEvent(PopularResults results) {
    this.results = results;
  }

  public PopularResults getResults() {
    return results;
  }

  public void setResults(PopularResults results) {
    this.results = results;
  }
}
