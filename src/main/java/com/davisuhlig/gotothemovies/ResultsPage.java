package com.davisuhlig.gotothemovies;

import java.util.List;

/**
 * Created by duhlig on 8/2/17.
 */
public class ResultsPage {
    List<Movie> results;

    public ResultsPage() {
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
