package entity;

import business.MovieManager;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Movie {
    private int id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private double rating;

    public Movie() {
    }

    public Movie(int id, String title, String director, LocalDate releaseDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d - Title: %s - Director: %s - Release Date: %s - Rating: %1f",
                this.id, this.title, this.director, formatter.format(this.releaseDate), this.rating);
    }

    public void inputData(Scanner scanner) {
        this.id = inputId(scanner);
        this.title = inputTitle(scanner);
        this.director = inputDirector(scanner);
        this.releaseDate = inputReleaseDate(scanner);
        this.rating = inputRating(scanner);
    }

    public int inputId(Scanner scanner, MovieManager<Movie> movieManager) {
        do {
            System.out.println("Nhập ID phim:");
            String idInput = scanner.nextLine();
            if (Validation.isPositiveInteger(idInput)) {
                int id = Integer.parseInt(idInput);
                boolean isExist = movieManager.movieList.stream()
                        .anyMatch(item -> item.getId() == id);
                if (!isExist) {
                    return id;
                } else {
                    System.err.println("ID này đã tồn tại!");
                }
            } else {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        
    }

    public String inputDirector(Scanner scanner) {
    }

    public LocalDate inputReleaseDate(Scanner scanner) {
    }

    public double inputRating(Scanner scanner) {
    }
}
