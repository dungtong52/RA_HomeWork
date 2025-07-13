import business.MovieManagement;
import business.imp.MovieManagementImp;
import validator.Validation;

import java.util.Scanner;

public class Main {
    private final MovieManagement movieManagement;

    public Main() {
        movieManagement = new MovieManagementImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        do {
            System.out.println("************* Movie Management *************");
            System.out.println("1. Add Movie");
            System.out.println("2. Show List Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. EXIT");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 5)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.movieManagement.createMovie(scanner);
                    case 2 -> main.movieManagement.displayAllMovie();
                    case 3 -> main.movieManagement.updateMovie(scanner);
                    case 4 -> main.movieManagement.deleteMovie(scanner);
                    default -> System.exit(0);
                }
            }
        } while (true);
    }
}