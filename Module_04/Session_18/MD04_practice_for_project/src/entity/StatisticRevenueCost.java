package entity;

import java.time.LocalDate;

public class StatisticRevenueCost {
    private float totalAmount;
    private int date;
    private int month;
    private int year;

    public StatisticRevenueCost() {
    }

    public StatisticRevenueCost(float totalAmount, int date, int month, int year) {
        this.totalAmount = totalAmount;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%2d/%2d/%2d: %6.1f", this.date, this.month, this.year, this.totalAmount);
    }
}
