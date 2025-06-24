import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào 1 số nguyên: ");
        int n = Integer.parseInt(sc.nextLine());

        // Số 0 cũng là số chẵn nên đề bài ở đây hơi sai
        if (n == 0)
			System.out.println("Số không phải chẵn cũng không phải lẻ");
        else if (n % 2 == 0)
			System.out.printf("Số %d là số chẵn%n", n);
        else
			System.out.printf("Số %d là số lẻ%n", n);
    }
}
