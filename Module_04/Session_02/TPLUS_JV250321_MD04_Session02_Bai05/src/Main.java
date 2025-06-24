import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập 1 số nguyên từ 1 đến 12: ");
            if (sc.hasNextInt()) {
                int month = Integer.parseInt(sc.nextLine());
                switch (month) {
                    case 1, 3, 5, 7, 8, 10, 12:
                        System.out.printf("Tháng %d có 31 ngày", month);
                        break;
                    case 2:
                        System.out.printf("Tháng %d có 28 hoặc 29 ngày", month);
                        break;
                    case 4, 6, 9, 11:
                        System.out.printf("Tháng %d có 30 ngày", month);
                        break;
                    default:
                        System.out.print("Tháng không hợp lệ.");
                }
                break;
            } else sc.next();
            System.out.println("Vui lòng nhập vào một số nguyên và từ 1 đến 12");
        }
        sc.close();
    }
}
