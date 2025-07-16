package business;

import entity.Movie;
import entity.Showtime;

import java.util.List;

public interface ShowtimeBusiness {
    boolean addShowtime(Showtime showtime);

    boolean checkExistShowtime(int showtimeId);

    boolean updateShowtime(Showtime showtime);

    boolean deleteShowtime(int showtimeId);

    List<Showtime> getShowtimeByMovie(int movieId);
}
