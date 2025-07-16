package business;

import entity.Movie;

import java.util.List;

public interface MovieBusiness {
    boolean addMovie(Movie movie);

    boolean checkExistMovie(int movieId);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(int movieId);

    List<Movie> getAllMovie();
}
