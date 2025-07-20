package presentation;

import business.StatisticBusiness;
import business.imp.StatisticBusinessImp;
import entity.StatisticEmployee;
import entity.StatisticProduct;
import entity.StatisticRevenueCost;
import validation.Validation;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReportManagement {
    private final StatisticBusiness statisticBusiness;

    public ReportManagement() {
        statisticBusiness = new StatisticBusinessImp();
    }

    public void reportMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n**************** REPORT MANAGEMENT ***************");
            System.out.println("1. Thống kê chi phí theo ngày, tháng, năm");
            System.out.println("2. Thống kê chi phí theo khoảng thời gian");
            System.out.println("3. Thống kê doanh thu theo ngày, tháng, năm");
            System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
            System.out.println("5. Thống kê số nhân viên theo từng trạng thái");
            System.out.println("6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
            System.out.println("7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
            System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
            System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
            System.out.println("10. Thoát");
            System.out.println("Lựa chọn của bạn:");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 10)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        statisticReceipt(scanner);
                        break;
                    case 2:
                        statisticReceiptInRange(scanner);
                        break;
                    case 3:
                        statisticBill(scanner);
                        break;
                    case 4:
                        statisticBillInRange(scanner);
                        break;
                    case 5:
                        statisticEmpByStatus(scanner);
                        break;
                    case 6:
                        statisticProductMaxBuyInRange(scanner);
                        break;
                    case 7:
                        statisticProductMinBuyInRange(scanner);
                        break;
                    case 8:
                        statisticProductMaxSellInRange(scanner);
                        break;
                    case 9:
                        statisticProductMinSellInRange(scanner);
                        break;
                    default:
                        exit = true;
                }
            } else {
                System.err.println("Nhập vào số nguyên trong phạm vi 1-10");
            }
        }
    }

    public void statisticReceipt(Scanner scanner) {
        System.out.println("Thống kê chi phí theo ngày");
        List<StatisticRevenueCost> statisticRevenueCosts1 = statisticBusiness.statisticRevenueCostByDate("date", 1);
        statisticRevenueCosts1.forEach(System.out::println);

        System.out.println("Thống kê chi phí theo tháng");
        List<StatisticRevenueCost> statisticRevenueCosts2 = statisticBusiness.statisticRevenueCostByDate("month", 1);
        statisticRevenueCosts2.forEach(System.out::println);

        System.out.println("Thống kê chi phí theo năm");
        List<StatisticRevenueCost> statisticRevenueCosts3 = statisticBusiness.statisticRevenueCostByDate("year", 1);
        statisticRevenueCosts3.forEach(System.out::println);
    }

    public void statisticReceiptInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();
            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                List<StatisticRevenueCost> statisticRevenueCosts =
                        statisticBusiness.statisticRevenueCostInRange(1, LocalDate.parse(startDate), LocalDate.parse(endDate));
                statisticRevenueCosts.forEach(System.out::println);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }

    public void statisticBill(Scanner scanner) {
        System.out.println("Thống kê doanh thu theo ngày");
        List<StatisticRevenueCost> statisticRevenueCosts1 = statisticBusiness.statisticRevenueCostByDate("date", 0);
        statisticRevenueCosts1.forEach(System.out::println);

        System.out.println("Thống kê doanh thu theo tháng");
        List<StatisticRevenueCost> statisticRevenueCosts2 = statisticBusiness.statisticRevenueCostByDate("month", 0);
        statisticRevenueCosts2.forEach(System.out::println);

        System.out.println("Thống kê doanh thu theo năm");
        List<StatisticRevenueCost> statisticRevenueCosts3 = statisticBusiness.statisticRevenueCostByDate("year", 0);
        statisticRevenueCosts3.forEach(System.out::println);
    }

    public void statisticBillInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();
            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                List<StatisticRevenueCost> statisticRevenueCosts =
                        statisticBusiness.statisticRevenueCostInRange(0, LocalDate.parse(startDate), LocalDate.parse(endDate));
                statisticRevenueCosts.forEach(System.out::println);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }

    public void statisticEmpByStatus(Scanner scanner) {
        System.out.println("Thống kê nhân viên theo trạng thái");
        List<StatisticEmployee> statisticEmployeeList = statisticBusiness.statisticEmployeeByStatus();
        statisticEmployeeList.forEach(System.out::println);
    }

    public void statisticProductMaxBuyInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                System.out.println("Sản phẩm nhập nhiều nhất");
                StatisticProduct statisticProduct = statisticBusiness.statisticProductInRange("max", 1, LocalDate.parse(startDate), LocalDate.parse(endDate));
                System.out.println(statisticProduct);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }

    public void statisticProductMinBuyInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                System.out.println("Sản phẩm nhập ít nhất");
                StatisticProduct statisticProduct = statisticBusiness.statisticProductInRange("min", 1, LocalDate.parse(startDate), LocalDate.parse(endDate));
                System.out.println(statisticProduct);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }

    public void statisticProductMaxSellInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                System.out.println("Sản phẩm xuất nhiều nhất");
                StatisticProduct statisticProduct = statisticBusiness.statisticProductInRange("max", 0, LocalDate.parse(startDate), LocalDate.parse(endDate));
                System.out.println(statisticProduct);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }

    public void statisticProductMinSellInRange(Scanner scanner) {
        while (true) {
            System.out.println("Nhập vào khoảng thời gian cần thống kê. Ngày bắt đầu (yyyy-MM-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Ngày kết thúc (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            if (Validation.isValidDate(startDate, "yyyy-MM-dd") && Validation.isValidDate(endDate, "yyyy-MM-dd")) {
                System.out.println("Sản phẩm xuất ít nhất");
                StatisticProduct statisticProduct = statisticBusiness.statisticProductInRange("min", 0, LocalDate.parse(startDate), LocalDate.parse(endDate));
                System.out.println(statisticProduct);
                break;
            } else {
                System.err.println("Thông tin nhập không đúng định dạng");
            }
        }
    }
}
