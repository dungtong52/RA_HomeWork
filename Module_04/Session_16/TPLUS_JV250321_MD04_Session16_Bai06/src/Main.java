import business.EmployeeProjectBusiness;
import business.imp.EmployeeProjectBusinessImp;

import java.util.Scanner;

public class Main {
    private final EmployeeProjectBusiness employeeProjectBusiness;

    public Main() {
        employeeProjectBusiness = new EmployeeProjectBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.employeeProjectBusiness.assignEmployeeToProject(scanner);
    }
}
