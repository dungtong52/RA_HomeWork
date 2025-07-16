package dao.imp;

import dao.MovieDAO;
import entity.Movie;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImp implements MovieDAO {
    @Override
    public boolean addMovie(Movie movie) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_movie(?,?,?,?,?)}");
            callableStatement.setString(1, movie.getTitle());
            callableStatement.setString(2, movie.getDirector());
            callableStatement.setInt(3, movie.getDuration());
            callableStatement.setDate(4, Date.valueOf(movie.getRelease_date()));
            callableStatement.setBoolean(5, movie.isStatus());
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
    public boolean checkExistMovie(int movieId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_movie(?)}");
            callableStatement.setInt(1, movieId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_movie(?,?,?,?,?,?)}");
            callableStatement.setInt(1, movie.getMovieId());
            callableStatement.setString(2, movie.getTitle());
            callableStatement.setString(3, movie.getDirector());
            callableStatement.setInt(4, movie.getDuration());
            callableStatement.setDate(5, Date.valueOf(movie.getRelease_date()));
            callableStatement.setBoolean(6, movie.isStatus());
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

    @Override
    public List<Movie> getAllMovie() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Movie> movieList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_movie()}");
            ResultSet resultSet = callableStatement.executeQuery();
            movieList = new ArrayList<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setMovieId(resultSet.getInt("movie_id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDuration(resultSet.getInt("duration"));
                movie.setRelease_date(resultSet.getDate("release_date").toLocalDate());
                movie.setStatus(resultSet.getBoolean("status"));
                movieList.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movieList;
    }
}
