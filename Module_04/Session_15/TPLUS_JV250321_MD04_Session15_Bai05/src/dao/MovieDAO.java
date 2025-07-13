package dao;

import entity.Movie;

import java.util.List;

public interface MovieDAO {
    boolean createMovie(Movie movie);

    List<Movie> showAll();

    Movie searchMovieById(int movieId);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(int movieId);
}
