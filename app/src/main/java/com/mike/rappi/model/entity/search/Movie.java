package com.mike.rappi.model.entity.search;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 2/03/18.
 */

public class Movie extends RealmObject {

  @SerializedName("poster_path")
  private String posterPath;
  @SerializedName("adult")
  private boolean adult;
  @SerializedName("overview")
  private String overview;
  @SerializedName("release_date")
  private String releaseDate;

  @PrimaryKey
  @SerializedName("id")
  private Integer id;
  @SerializedName("original_title")
  private String originalTitle;
  @SerializedName("original_language")
  private String originalLanguage;
  @SerializedName("title")
  private String title;
  @SerializedName("backdrop_path")
  private String backdropPath;
  @SerializedName("popularity")
  private Double popularity;
  @SerializedName("vote_count")
  private Integer voteCount;
  @SerializedName("video")
  private Boolean video;
  @SerializedName("vote_average")
  private Double voteAverage;

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public Double getPopularity() {
    return popularity;
  }

  public void setPopularity(Double popularity) {
    this.popularity = popularity;
  }

  public Integer getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }

  public Boolean getVideo() {
    return video;
  }

  public void setVideo(Boolean video) {
    this.video = video;
  }

  public Double getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(Double voteAverage) {
    this.voteAverage = voteAverage;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "posterPath='" + posterPath + '\'' +
        ", adult=" + adult +
        ", overview='" + overview + '\'' +
        ", releaseDate='" + releaseDate + '\'' +

        ", id=" + id +
        ", originalTitle='" + originalTitle + '\'' +
        ", originalLanguage='" + originalLanguage + '\'' +
        ", title='" + title + '\'' +
        ", backdropPath='" + backdropPath + '\'' +
        ", popularity=" + popularity +
        ", voteCount=" + voteCount +
        ", video=" + video +
        ", voteAverage=" + voteAverage +
        '}';
  }
}
