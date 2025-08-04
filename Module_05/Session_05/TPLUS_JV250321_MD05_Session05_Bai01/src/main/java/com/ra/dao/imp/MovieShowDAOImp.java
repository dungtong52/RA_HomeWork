package com.ra.dao.imp;

import com.ra.dao.MovieShowDAO;
import com.ra.model.Movie;
import com.ra.model.MovieShow;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieShowDAOImp implements MovieShowDAO {
    @Override
    public List<MovieShow> getMovieListShow() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<MovieShow> movieShowList = new ArrayList<>();
        Map<Long, MovieShow> movieShowMap = new HashMap<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call show_movie_with_showtime()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                long movieId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration");
                String language = resultSet.getString("language");

                MovieShow movieShow = movieShowMap.get(movieId);
                if(movieShow == null){
                    Movie movie = new Movie(movieId, title, director, genre, description, duration, language);
                    movieShow = new MovieShow(movie);
                    movieShowMap.put(movieId, movieShow);
                }
                movieShow.addShowtime(resultSet.getTimestamp("show_time").toLocalDateTime());
            }
            movieShowList.addAll(movieShowMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movieShowList;
    }
}
