package com.ra.service.imp;

import com.ra.dao.MovieDAO;
import com.ra.dao.imp.MovieDAOImp;
import com.ra.model.Movie;

import java.util.List;

public class MovieServiceImp implements com.ra.service.MovieService {
    private final MovieDAO movieDAO;

    public MovieServiceImp() {
        movieDAO = new MovieDAOImp();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    @Override
    public Movie getMovieById(long id) {
        return movieDAO.getMovieById(id);
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    @Override
    public boolean deleteMovie(long id) {
        return movieDAO.deleteMovie(id);
    }
}
