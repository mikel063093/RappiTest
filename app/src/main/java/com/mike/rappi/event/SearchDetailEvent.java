package com.mike.rappi.event;

import com.mike.rappi.model.entity.search.Movie;

/**
 * Created by mike
 */

public class SearchDetailEvent {
  private Movie results;

  public SearchDetailEvent(Movie results) {
    this.results = results;
  }

  public Movie getResults() {
    return results;
  }

  public void setResults(Movie results) {
    this.results = results;
  }
}
