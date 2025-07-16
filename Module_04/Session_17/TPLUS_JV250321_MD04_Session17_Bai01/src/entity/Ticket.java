package entity;

public class Ticket {
    private int ticketId;
    private int showtimeId;
    private String seatNumber;
    private String customerName;

    public Ticket() {
    }

    public Ticket(int ticketId, int showtimeId, String seatNumber, String customerName) {
        this.ticketId = ticketId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", showtimeId=" + showtimeId +
                ", seatNumber='" + seatNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
