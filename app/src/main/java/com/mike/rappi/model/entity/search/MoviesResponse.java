package com.mike.rappi.model.entity.search;

import com.google.gson.annotations.SerializedName;

import com.mike.rappi.model.entity.popular.PopularResults;
import java.util.List;

/**
 * Created by JkVillavo12Col on 2/03/18.
 */

public class MoviesResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<PopularResults> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public MoviesResponse(int page, List<PopularResults> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PopularResults> getResults() {
        return results;
    }

    public void setResults(List<PopularResults> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "page=" + page +
                ", results=" + results +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
