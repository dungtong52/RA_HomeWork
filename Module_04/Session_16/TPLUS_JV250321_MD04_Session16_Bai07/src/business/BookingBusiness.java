package business;

import entity.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface BookingBusiness {
    boolean bookRoom(int roomId, String customerName, LocalDate startDate, LocalDate endDate);
    boolean cancelBooking(int bookingId);
    List<Room> listAvailableRooms();
}
