package presentation;

import business.EmployeeBusiness;
import business.imp.EmployeeBusinessImp;
import entity.Employee;
import business.PaginationBusiness;
import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EmployeeManagement {
    private final int ID_MAX_LENGTH = 5;
    private final int MAX_LENGTH = 100;


    private final EmployeeBusiness employeeBusiness;
    private final PaginationBusiness<Employee> paginationBusiness;

    public EmployeeManagement() {
        employeeBusiness = new EmployeeBusinessImp();
        paginationBusiness = new EmployeeBusinessImp();
    }

    public void employeeMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** EMPLOYEE MANAGEMENT ***************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Cập nhật trạng thái nhân viên");
            System.out.println("5. Tìm kiếm nhân viên theo tên nhân viên hoặc mã nhân viên");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn:");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        PaginationPresentation.getListPagination(scanner, paginationBusiness, "employees", "");
                        break;
                    case 2:
                        addEmployee(scanner);
                        break;
                    case 3:
                        updateEmployee(scanner);
                        break;
                    case 4:
                        updateEmpStatus(scanner);
                        break;
                    case 5:
                        System.out.println("Chọn cách thức tìm kiếm nhân viên");
                        System.out.println("1. Tìm kiếm theo tên nhân viên");
                        System.out.println("2. Tìm kiếm theo mã nhân viên");
                        System.out.println("Lựa chọn: ");
                        String numberChoice = scanner.nextLine();
                        if (Validation.isIntegerInRange(numberChoice, 1, 2)) {
                            if (Integer.parseInt(numberChoice) == 1) {
                                getEmployeeByName(scanner);
                            } else {
                                getEmployeeById(scanner);
                            }
                        } else {
                            System.out.println("Số nhập vào không hợp lệ");
                        }
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-6");
            }
        }
    }

    public void addEmployee(Scanner scanner) {
        Employee employee = new Employee();
        employee.setEmpId(inputEmployeeIDForCreate(scanner));
        employee.setEmpName(inputEmployeeName(scanner));
        employee.setBirthOfDate(inputBirthOfDate(scanner));
        employee.setEmail(inputEmail(scanner));
        employee.setPhone(inputPhone(scanner));
        employee.setAddress(inputAddress(scanner));
        employee.setEmpStatus(inputStatus(scanner));
        boolean success = employeeBusiness.createEmployee(employee);
        if (success) {
            System.out.println("Thêm mới nhân viên thành công.");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới.");
        }
    }

    public void updateEmployee(Scanner scanner) {
        String employeeId = inputEmployeeIDForUpdate(scanner);
        Employee updateEmployee = employeeBusiness.getEmployeeById(employeeId);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Cập nhật tên nhân viên");
            System.out.println("2. Cập nhật ngày sinh");
            System.out.println("3. Cập nhật email");
            System.out.println("4. Cập nhật số điện thoại");
            System.out.println("5. Cập nhật địa chỉ");
            System.out.println("6. Thoát cập nhật");
            System.out.println("Lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        updateEmployee.setEmpName(inputEmployeeName(scanner));
                        break;
                    case 2:
                        updateEmployee.setBirthOfDate(inputBirthOfDate(scanner));
                        break;
                    case 3:
                        updateEmployee.setEmail(inputEmail(scanner));
                        break;
                    case 4:
                        updateEmployee.setPhone(inputPhone(scanner));
                        break;
                    case 5:
                        updateEmployee.setAddress(inputAddress(scanner));
                        break;
                    default:
                        exit = true;
                }
            }
        }
        if (employeeBusiness.updateEmployee(updateEmployee)) {
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Cập nhật thất bại!");
        }
    }

    public void getEmployeeByName(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào tên tương đối để tìm nhân viên: ");
            String employeeName = scanner.nextLine();
            if (Validation.isValidLength(employeeName, MAX_LENGTH)) {
                PaginationPresentation.getListPagination(scanner, paginationBusiness, "employees", employeeName);
                break;
            } else {
                System.err.printf("Tên nhập vào không được để trống hoặc quá %d ký tự!\n", MAX_LENGTH);
            }
        }
    }

    public void getEmployeeById(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào mã nhân viên: ");
            String employeeId = scanner.nextLine();
            if (Validation.isValidLength(employeeId, ID_MAX_LENGTH)) {
                Employee employee = employeeBusiness.getEmployeeById(employeeId);
                if (employee != null) {
                    System.out.println(employee);
                    break;
                } else {
                    System.err.println("Không tìm thấy nhân viên có mã " + employeeId);
                }
            } else {
                System.err.printf("Tên nhập vào không được để trống hoặc quá %d ký tự!\n", ID_MAX_LENGTH);
            }
        }
    }

    public void updateEmpStatus(Scanner scanner) {
        String employeeId = inputEmployeeIDForUpdate(scanner);
        Employee updateEmployee = employeeBusiness.getEmployeeById(employeeId);
        short statusCurrent = updateEmployee.getEmpStatus();
        System.out.printf("Trạng thái hiện tại của nhân viên có ID %s: %s\n",
                updateEmployee.getEmpId(),
                statusCurrent == 0 ? "Hoạt động" : (statusCurrent == 1 ? "Nghỉ chế độ" : "Nghỉ việc"));
        while (true) {
            System.out.println("Cập nhật trạng thái mới (0. Hoạt động | 1. Nghỉ chế độ | 2. Nghỉ việc): ");
            String statusInput = scanner.nextLine();
            if (Validation.isIntegerInRange(statusInput, 0, 2)) {
                short status = Short.parseShort(statusInput);
                if (employeeBusiness.updateEmployeeStatus(employeeId, status)) {
                    System.out.println("Cập nhật trạng thái thành công");
                    break;
                } else {
                    System.err.println("Cập nhật trạng thái thất bại!");
                }
            } else {
                System.err.println("Trạng thái nhập vào không đúng!");
            }
        }
    }

    public String inputEmployeeIDForCreate(Scanner scanner) {
        while (true) {
            System.out.println("Nhập mã nhân viên: ");
            String employeeID = scanner.nextLine();
            if (Validation.isValidLength(employeeID, ID_MAX_LENGTH)) {
                if (employeeBusiness.getEmployeeById(employeeID) == null) {
                    return employeeID;
                } else {
                    System.err.println("Mã nhân viên này đã tồn tại. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", ID_MAX_LENGTH);
            }
        }
    }

    public String inputEmployeeIDForUpdate(Scanner scanner) {
        while (true) {
            System.out.println("Nhập mã nhân viên: ");
            String employeeID = scanner.nextLine();
            if (Validation.isValidLength(employeeID, ID_MAX_LENGTH)) {
                if (employeeBusiness.getEmployeeById(employeeID) != null) {
                    return employeeID;
                } else {
                    System.err.println("Mã nhân viên này KHÔNG tồn tại. Vui lòng nhập mã khác!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", ID_MAX_LENGTH);
            }
        }
    }

    public String inputEmployeeName(Scanner scanner) {
        while (true) {
            System.out.println("Nhập tên nhân viên: ");
            String employeeName = scanner.nextLine();
            if (Validation.isValidLength(employeeName, MAX_LENGTH)) {
                if (!employeeBusiness.checkExistEmployeeName(employeeName)) {
                    return employeeName;
                } else {
                    System.err.println("Tên nhân viên đã tồn tại. Vui lòng nhập lại!");
                }
            } else {
                System.err.printf("Thông tin nhập không được để trống hoặc có độ dài quá %d ký tự. Vui lòng nhập lại!\n", MAX_LENGTH);
            }
        }
    }

    public LocalDate inputBirthOfDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Nhập vào ngày sinh (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            if (Validation.isValidDate(date, "yyyy-MM-dd")) {
                return LocalDate.parse(date, formatter);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }

    public String inputEmail(Scanner scanner) {
        while (true) {
            System.out.println("Nhập địa chỉ email: ");
            String email = scanner.nextLine();
            if (Validation.isValidEmail(email)) {
                return email;
            } else {
                System.err.println("Thông tin nhập không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputPhone(Scanner scanner) {
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            String phone = scanner.nextLine();
            if (Validation.isValidPhone(phone)) {
                return phone;
            } else {
                System.err.println("Thông tin nhập không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputAddress(Scanner scanner) {
        while (true) {
            System.out.println("Nhập địa chỉ: ");
            String address = scanner.nextLine();
            if (Validation.isNotEmpty(address)) {
                return address;
            } else {
                System.err.println("Thông tin nhập không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public short inputStatus(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào trạng thái: ");
            String status = scanner.nextLine();
            if (status.equals("0") || status.equals("1") || status.equals("2")) {
                return Short.parseShort(status);
            } else {
                System.err.println("Thông tin nhập vào không đúng định dạng. Vui lòng nhập lại!");
            }
        }
    }
}
