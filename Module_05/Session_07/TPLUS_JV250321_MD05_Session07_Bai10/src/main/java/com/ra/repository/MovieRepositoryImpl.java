package com.ra.repository;

import com.ra.model.Movie;
import com.ra.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public boolean save(Movie movie) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL add_movie(?, ?, ?, ?, ?)}");
            cs.setString(1, movie.getTitle());
            cs.setString(2, movie.getDirector());
            cs.setDate(3, movie.getReleaseDate() == null ? null : Date.valueOf(movie.getReleaseDate()));
            cs.setString(4, movie.getGenre());
            cs.setString(5, movie.getPoster());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL update_movie(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, movie.getId());
            cs.setString(2, movie.getTitle());
            cs.setString(3, movie.getDirector());
            cs.setDate(4, movie.getReleaseDate() == null ? null : Date.valueOf(movie.getReleaseDate()));
            cs.setString(5, movie.getGenre());
            cs.setString(6, movie.getPoster());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL delete_movie(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return false;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL get_all_movies()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
                movie.setGenre(rs.getString("genre"));
                movie.setPoster(rs.getString("poster"));
                list.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return list;
    }

    @Override
    public Movie findMovieById(int id) {
        Connection conn = null;
        CallableStatement cs = null;
        Movie movie = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL get_movie_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
                movie.setGenre(rs.getString("genre"));
                movie.setPoster(rs.getString("poster"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return movie;
    }
}
