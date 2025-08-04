package com.ra.dao;

import com.ra.model.Movie;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies();

    Movie getMovieById(long id);

    boolean addMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(long id);
}
