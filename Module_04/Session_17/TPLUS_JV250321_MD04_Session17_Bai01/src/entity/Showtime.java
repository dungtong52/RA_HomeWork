package entity;

import java.time.LocalDateTime;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private LocalDateTime showTime;
    private int totalSeats;
    private int availableSeats;
    private boolean status;

    public Showtime() {
    }

    public Showtime(int showtimeId, int movieId, LocalDateTime showTime, int totalSeats, int availableSeats, boolean status) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.status = status;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "showtimeId=" + showtimeId +
                ", movieId=" + movieId +
                ", showTime=" + showTime +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", status=" + status +
                '}';
    }
}
