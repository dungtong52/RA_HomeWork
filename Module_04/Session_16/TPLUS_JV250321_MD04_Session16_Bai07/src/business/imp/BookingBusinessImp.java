package business.imp;

import business.BookingBusiness;
import dao.BookingDAO;
import dao.imp.BookingDAOImp;
import entity.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BookingBusinessImp implements BookingBusiness {
    private final BookingDAO bookingDAO;

    public BookingBusinessImp() {
        bookingDAO = new BookingDAOImp();
    }

    @Override
    public boolean bookRoom(int roomId, String customerName, LocalDate startDate, LocalDate endDate) {
        return bookingDAO.bookRoom(roomId, customerName, startDate, endDate);
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }

    @Override
    public List<Room> listAvailableRooms() {
        return bookingDAO.listAvailableRooms();
    }
}
