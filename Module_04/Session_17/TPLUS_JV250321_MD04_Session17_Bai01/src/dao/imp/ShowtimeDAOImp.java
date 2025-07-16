package dao.imp;

import dao.ShowtimeDAO;
import entity.Movie;
import entity.Showtime;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDAOImp implements ShowtimeDAO {
    @Override
    public boolean addShowtime(Showtime showtime) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call create_showtime(?,?,?,?,?)}");
            callableStatement.setInt(1, showtime.getMovieId());
            callableStatement.setTimestamp(2, Timestamp.valueOf(showtime.getShowTime()));
            callableStatement.setInt(3, showtime.getTotalSeats());
            callableStatement.setInt(4, showtime.getAvailableSeats());
            callableStatement.setBoolean(5, showtime.isStatus());
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
    public boolean checkExistShowtime(int showtimeId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call check_exist_showtime(?)}");
            callableStatement.setInt(1, showtimeId);
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
    public boolean updateShowtime(Showtime showtime) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_showtime(?,?,?,?,?,?)}");
            callableStatement.setInt(1, showtime.getShowtimeId());
            callableStatement.setInt(2, showtime.getMovieId());
            callableStatement.setTimestamp(3, Timestamp.valueOf(showtime.getShowTime()));
            callableStatement.setInt(4, showtime.getTotalSeats());
            callableStatement.setInt(5, showtime.getAvailableSeats());
            callableStatement.setBoolean(6, showtime.isStatus());
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
    public boolean deleteShowtime(int showtimeId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_showtime(?)}");
            callableStatement.setInt(1, showtimeId);
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
    public List<Showtime> getShowtimeByMovie(int movieId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Showtime> showtimeList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call get_showtime_by_movie(?)}");
            callableStatement.setInt(1, movieId);
            ResultSet resultSet = callableStatement.executeQuery();
            showtimeList = new ArrayList<>();
            while (resultSet.next()) {
                Showtime showtime = new Showtime();
                showtime.setShowtimeId(resultSet.getInt("showtime_id"));
                showtime.setMovieId(resultSet.getInt("movie_id"));
                showtime.setShowTime(resultSet.getTimestamp("show_time").toLocalDateTime());
                showtime.setTotalSeats(resultSet.getInt("total_seats"));
                showtime.setAvailableSeats(resultSet.getInt("available_seats"));
                showtime.setStatus(resultSet.getBoolean("status"));
                showtimeList.add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return showtimeList;
    }
}
