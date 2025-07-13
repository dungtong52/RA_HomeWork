package entity;

import validator.Validation;

import java.time.LocalDate;
import java.util.Scanner;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;

    public Movie() {
    }

    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner) {
        this.title = inputTitle(scanner);
        this.director = inputDirector(scanner);
        this.year = inputYear(scanner);
    }

    public String inputTitle(Scanner scanner) {
        do {
            System.out.print("Nhập vào tên phim: ");
            String title = scanner.nextLine();
            if (Validation.isNotEmpty(title)) {
                if (title.length() < 100) {
                    return title;
                } else {
                    System.err.println("Độ dài quá 100 ký tự!");
                }
            } else {
                System.err.println("Không được để trống!");
            }
        } while (true);
    }

    public String inputDirector(Scanner scanner) {
        do {
            System.out.print("Nhập vào tên đạo diễn: ");
            String director = scanner.nextLine();
            if (title.length() < 100) {
                return director;
            } else {
                System.err.println("Độ dài quá 100 ký tự!");
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        do {
            System.out.print("Nhập vào năm chiếu: ");
            String year = scanner.nextLine();
            if (Validation.isPositiveInteger(year)) {
                return Integer.parseInt(year);
            } else {
                System.err.println("Nhập vào số nguyên dương!");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Title: %s - Director: %s - Year: %s",
                this.id, this.title, this.director, this.year);
    }
}
