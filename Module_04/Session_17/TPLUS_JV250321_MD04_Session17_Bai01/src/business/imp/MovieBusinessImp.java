package business.imp;

import business.MovieBusiness;
import dao.MovieDAO;
import dao.imp.MovieDAOImp;
import entity.Movie;

import java.util.List;

public class MovieBusinessImp implements MovieBusiness {
    private final MovieDAO movieDAO = new MovieDAOImp();

    @Override
    public boolean addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }

    @Override
    public boolean checkExistMovie(int movieId) {
        return movieDAO.checkExistMovie(movieId);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    @Override
    public boolean deleteMovie(int movieId) {
        return movieDAO.deleteMovie(movieId);
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieDAO.getAllMovie();
    }
}
