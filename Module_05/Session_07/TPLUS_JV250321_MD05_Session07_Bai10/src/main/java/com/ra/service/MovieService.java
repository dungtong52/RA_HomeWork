package com.ra.service;

import com.ra.model.Movie;

import java.util.List;

public interface MovieService {
    boolean saveMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(int id);

    List<Movie> findAll();

    Movie findMovieById(int id);

}
