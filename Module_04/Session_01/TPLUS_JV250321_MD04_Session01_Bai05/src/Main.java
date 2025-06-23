import java.util.Scanner;

public class Main {
	// Tìm ước chung lớn nhất
	public static int findUCLN(int a, int b){
		while(b != 0){
			int temp = b;
			b = a % b;
			a = temp;
		}
		return Math.abs(a);
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tử số phân số thứ 1: ");
		int a = Integer.parseInt(sc.nextLine());
		System.out.print("Nhập mẫu số phân số thứ 1: ");
		int b = Integer.parseInt(sc.nextLine());
		System.out.print("Nhập tử số phân số thứ 2: ");
		int c = Integer.parseInt(sc.nextLine());
		System.out.print("Nhập mẫu số phân số thứ 2: ");
		int d = Integer.parseInt(sc.nextLine());

		int tuSo = a*d + b*c;
		int mauSo = b*d;
		int ucln = findUCLN(tuSo, mauSo);

		System.out.printf("kết quả: %d/%d", tuSo/ucln, mauSo/ucln);
	}
}
