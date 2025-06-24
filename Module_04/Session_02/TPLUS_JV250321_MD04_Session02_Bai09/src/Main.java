import java.util.Scanner;

public class Main {
    public static boolean isArmstrongNumber(int number) {
        int k = 0;
        double sum = 0;
        int temp = number;

        while (temp != 0) {
            temp /= 10;
            k++;
        }

        temp = number;
        while (temp != 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, k);
            temp /= 10;
        }

        return (sum == number);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập vào số nguyên dương N: ");
            if (sc.hasNextInt()) {
                int n = Integer.parseInt(sc.nextLine());
                if (n <= 0) System.out.println("Số nhập vào không hợp lệ");
                else {
                    String result = "";
                    for (int i = 0; i <= n; i++) {
                        if (isArmstrongNumber(i)) {
                            result += i + ", ";     // Chưa xử lý được dấu , cuối cùng
                        }
                    }
                    System.out.println(result);
                }
                break;
            } else sc.next();
            System.out.println("Số nhập vào không hợp lệ");
        }
        sc.close();
    }
}
