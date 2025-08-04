package com.ra.dao.imp;

import com.ra.dao.MovieShowDAO;
import com.ra.model.Movie;
import com.ra.model.MovieShow;
import com.ra.model.Schedule;
import com.ra.model.ScreenRoom;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
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
                long movieId = resultSet.getLong("movie_id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration");
                String language = resultSet.getString("language");

                long scheduleId = resultSet.getLong("id");
                long screenRoomId = resultSet.getLong("screen_room_id");
                LocalDateTime showTime = resultSet.getTimestamp("show_time").toLocalDateTime();
                String format = resultSet.getString("format");
                int availableSeats = resultSet.getInt("available_seats");

                MovieShow movieShow = movieShowMap.get(movieId);
                if (movieShow == null) {
                    Movie movie = new Movie(movieId, title, director, genre, description, duration, language);

                    movieShow = new MovieShow();
                    movieShow.setMovie(movie);
                    movieShowMap.put(movieId, movieShow);

                    Schedule schedule = new Schedule(scheduleId, movieId, screenRoomId, showTime, format, availableSeats);
                    movieShow.addSchedule(schedule);
                }
            }
            movieShowList.addAll(movieShowMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return movieShowList;
    }

    @Override
    public ScreenRoom getScreenRoomByScheduleId(long id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ScreenRoom screenRoom = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_screen_room_by_schedule_id(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                screenRoom = new ScreenRoom();
                screenRoom.setId(resultSet.getLong("id"));
                screenRoom.setScreenRoomName(resultSet.getString("screen_room_name"));
                screenRoom.setTotalSeat(resultSet.getInt("total_seat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return screenRoom;
    }
}
