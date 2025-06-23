import java.util.Scanner;

public class Main {
	public static int sumOfSquares(int number){
		int sum = 0;
		while(number != 0){
			int digit = number % 10;
			sum += digit * digit;
			number /= 10;
		}
		return sum;
	}

	// Thuật toán 2 con trỏ để phát hiện chu kỳ lặp
	public static boolean isHappyNumber(int number){
		int slow = number;
		int fast = sumOfSquares(number);

		while (fast != 1 && fast != slow){
			slow = sumOfSquares(slow);
			fast = sumOfSquares(sumOfSquares(fast));
		}
		return fast == 1;
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Nhập vào số nguyên dương N: ");
			if(sc.hasNextInt()){
				int n = sc.nextInt();
				if (n <= 0) System.out.println("Số nhập vào không hợp lệ");
				else {
					String result = "";
					for(int i = 1; i <= n; i++){
						if(isHappyNumber(i))
							result += i + ", ";
					}
					System.out.println("Các số Happy nhỏ hơn hoặc bằng " + n + " là: " + result);
				}
				break;
			}else sc.next();
			System.out.println("Số nhập vào không hợp lệ");
		}
		sc.close();

	}
}
