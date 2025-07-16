package entity;

import java.time.LocalDate;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private int duration;
    private LocalDate release_date;
    private boolean status;

    public Movie() {
    }

    public Movie(int movieId, String title, String director, int duration, LocalDate release_date, boolean status) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.release_date = release_date;
        this.status = status;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", release_date=" + release_date +
                ", status=" + status +
                '}';
    }
}
