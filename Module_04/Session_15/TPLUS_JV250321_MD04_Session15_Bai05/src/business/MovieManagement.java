package business;

import java.util.Scanner;

public interface MovieManagement {
    void createMovie(Scanner scanner);
    void displayAllMovie();
    void updateMovie(Scanner scanner);
    void deleteMovie(Scanner scanner);
}
