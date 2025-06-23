import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true){
			int sum = 0;
			System.out.print("Nhập vào số nguyên N: ");
			if(sc.hasNextInt()){
				int n = sc.nextInt();
				n = Math.abs(n);
				while (n != 0) {
					sum += n % 10;
					n /= 10;
				}
				System.out.println("Tổng các chữ số là: " + sum);
				break;
			} else sc.next();
			System.out.println("Vui lòng nhập vào 1 số nguyên");
		}
		sc.close();
	}
}
