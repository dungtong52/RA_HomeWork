package entity;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int roomId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking() {
    }

    public Booking(int id, int roomId, String customerName, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.roomId = roomId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
