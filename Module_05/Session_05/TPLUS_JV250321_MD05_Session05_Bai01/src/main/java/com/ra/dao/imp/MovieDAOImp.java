package com.ra.dao.imp;

import com.ra.dao.MovieDAO;
import com.ra.model.Movie;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImp implements MovieDAO {
    @Override
    public List<Movie> getAllMovies() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Movie> movieList = null;
        Movie movie = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_all_movies()}");
            ResultSet resultSet = callableStatement.executeQuery();
            movieList = new ArrayList<>();
            while (resultSet.next()) {
                movie = new Movie();
                movie.setMovieId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDescription(resultSet.getString("description"));
                movie.setDuration(resultSet.getInt("duration"));
                movie.setLanguage(resultSet.getString("language"));
                movieList.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Movie movie = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call getMovieById(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                movie = new Movie();
                movie.setMovieId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDescription(resultSet.getString("description"));
                movie.setDuration(resultSet.getInt("duration"));
                movie.setLanguage(resultSet.getString("language"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movie;
    }

    @Override
    public boolean addMovie(Movie movie) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_movie(?,?,?,?,?,?)}");
            callableStatement.setString(1, movie.getTitle());
            callableStatement.setString(2, movie.getDirector());
            callableStatement.setString(3, movie.getGenre());
            callableStatement.setString(4, movie.getDescription());
            callableStatement.setInt(5, movie.getDuration());
            callableStatement.setString(6, movie.getLanguage());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
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
            callableStatement = connection.prepareCall("{call update_movie(?,?,?,?,?,?,?)}");
            callableStatement.setLong(1, movie.getMovieId());
            callableStatement.setString(2, movie.getTitle());
            callableStatement.setString(3, movie.getDirector());
            callableStatement.setString(4, movie.getGenre());
            callableStatement.setString(5, movie.getDescription());
            callableStatement.setInt(6, movie.getDuration());
            callableStatement.setString(7, movie.getLanguage());
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteMovie(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_movie(?)}");
            callableStatement.setLong(1, id);
            int rows = callableStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }
}
