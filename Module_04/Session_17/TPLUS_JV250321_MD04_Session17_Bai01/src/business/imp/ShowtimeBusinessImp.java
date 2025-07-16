package business.imp;

import business.ShowtimeBusiness;
import dao.ShowtimeDAO;
import dao.imp.ShowtimeDAOImp;
import entity.Showtime;

import java.util.List;

public class ShowtimeBusinessImp implements ShowtimeBusiness {
    private final ShowtimeDAO showtimeDAO = new ShowtimeDAOImp();

    @Override
    public boolean addShowtime(Showtime showtime) {
        return showtimeDAO.addShowtime(showtime);
    }

    @Override
    public boolean checkExistShowtime(int showtimeId) {
        return showtimeDAO.checkExistShowtime(showtimeId);
    }

    @Override
    public boolean updateShowtime(Showtime showtime) {
        return showtimeDAO.updateShowtime(showtime);
    }

    @Override
    public boolean deleteShowtime(int showtimeId) {
        return showtimeDAO.deleteShowtime(showtimeId);
    }

    @Override
    public List<Showtime> getShowtimeByMovie(int movieId) {
        return showtimeDAO.getShowtimeByMovie(movieId);
    }
}
