package com.mike.rappi.model.api;

import com.mike.rappi.model.entity.popular.PopularResponse;
import com.mike.rappi.model.entity.search.MoviesResponse;
import com.mike.rappi.model.entity.toprated.TopRatedResponse;
import com.mike.rappi.model.entity.upcoming.UpcomingResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mike
 */

public interface ApiSource {

  // Popular Movies
  @GET("movie/popular")
  Observable<PopularResponse> getPopularMovies(@Query("api_key") String apiKey,
      @Query("language") String language);

  // Top rated Movies
  @GET("movie/top_rated")
  Observable<TopRatedResponse> getTopRatedMovies(@Query("api_key") String apiKey,
      @Query("language") String language);

  // Upcoming movies
  @GET("movie/upcoming")
  Observable<UpcomingResponse> getUpcomingMovies(@Query("api_key") String apiKey,
      @Query("language") String language);

  @GET("search/movie")
  Observable<MoviesResponse> searchMovie(@Query("api_key") String apiKey,
      @Query("language") String language, @Query("query") String query);
}
