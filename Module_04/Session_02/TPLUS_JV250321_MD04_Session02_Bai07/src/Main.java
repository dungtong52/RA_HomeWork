import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào độ dài 3 cạnh của tam giác:");
        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        if (a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0) {
            if (a == b && a == c)
                System.out.println("Đây là tam giác đều");
            else if (a == b || a == c || b == c)
                System.out.println("Đây là tam giác cân");
            else if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a)
                System.out.println("Đây là tam giác vuông");
            else System.out.println("Đây là tam giác thường");
        } else System.out.println("Ba cạnh không tạo thành tam giác.");
    }
}
