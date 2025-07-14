package entity;

import java.sql.Date;

public class Booking {
    private int id;
    private int roomId;
    private String customerName;
    private Date startDate;
    private Date endDate;

    public Booking() {
    }

    public Booking(int id, int roomId, String customerName, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
