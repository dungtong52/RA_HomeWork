package com.ra.repository;

import com.ra.model.Movie;

import java.util.List;

public interface MovieRepository {
    boolean save(Movie movie);

    boolean update(Movie movie);

    boolean delete(int id);

    List<Movie> findAll();

    Movie findMovieById(int id);
}
