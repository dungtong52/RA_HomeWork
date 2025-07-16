package dao;

import entity.Movie;

import java.util.List;

public interface MovieDAO {
    boolean addMovie(Movie movie);

    boolean checkExistMovie(int movieId);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(int movieId);

    List<Movie> getAllMovie();
}
