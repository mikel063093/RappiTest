package com.mike.rappi.event;

import com.mike.rappi.model.entity.popular.PopularResults;

/**
 * Created by mike
 */

public class PopularDetailEvent {
  private PopularResults results;

  public PopularDetailEvent(PopularResults results) {
    this.results = results;
  }

  public PopularResults getResults() {
    return results;
  }

  public void setResults(PopularResults results) {
    this.results = results;
  }
}
