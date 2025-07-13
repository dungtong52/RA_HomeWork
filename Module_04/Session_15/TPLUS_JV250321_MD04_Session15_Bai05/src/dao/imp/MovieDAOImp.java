package dao.imp;

import dao.MovieDAO;
import entity.Movie;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImp implements MovieDAO {
    @Override
    public boolean createMovie(Movie movie) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_movie(?, ?, ?)}");

            callSt.setString(1, movie.getTitle());
            callSt.setString(2, movie.getDirector());
            callSt.setInt(3, movie.getYear());

            callSt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Movie> showAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Movie> movieList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_movies}");

            ResultSet resultSet = callSt.executeQuery();
            movieList = new ArrayList<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("movie_id"));
                movie.setTitle(resultSet.getString("movie_title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setYear(resultSet.getInt("year"));

                movieList.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movieList;
    }

    @Override
    public Movie searchMovieById(int movieId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Movie movie = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call find_movie_by_id(?)}");
            callableStatement.setInt(1, movieId);
            ResultSet resultSet = callableStatement.executeQuery();
            movie = new Movie();
            if (resultSet.next()) {
                movie.setId(resultSet.getInt("movie_id"));
                movie.setTitle(resultSet.getString("movie_title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setYear(resultSet.getInt("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movie;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_movie(?, ?, ?, ?)}");
            callableStatement.setInt(1, movie.getId());
            callableStatement.setString(2, movie.getTitle());
            callableStatement.setString(3, movie.getDirector());
            callableStatement.setInt(4, movie.getYear());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteMovie(int movieId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_movie(?)}");
            callableStatement.setInt(1, movieId);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
