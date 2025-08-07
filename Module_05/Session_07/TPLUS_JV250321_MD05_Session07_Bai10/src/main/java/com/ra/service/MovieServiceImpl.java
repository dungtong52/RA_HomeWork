package com.ra.service;

import com.ra.model.Movie;
import com.ra.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public boolean saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieRepository.update(movie);
    }

    @Override
    public boolean deleteMovie(int id) {
        return movieRepository.delete(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(int id) {
        return movieRepository.findMovieById(id);
    }
}
