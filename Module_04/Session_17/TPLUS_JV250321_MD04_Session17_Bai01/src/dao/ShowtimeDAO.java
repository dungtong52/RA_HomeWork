package dao;

import entity.Showtime;

import java.util.List;

public interface ShowtimeDAO {
    boolean addShowtime(Showtime showtime);

    boolean checkExistShowtime(int showtimeId);

    boolean updateShowtime(Showtime showtime);

    boolean deleteShowtime(int showtimeId);

    List<Showtime> getShowtimeByMovie(int movieId);
}
