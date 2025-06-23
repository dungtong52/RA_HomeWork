import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		// Input productName, quantity, unitPrice
		Scanner sc = new Scanner(System.in);
		System.out.print("Tên sản phẩm: ");
		String productName = sc.nextLine();

		System.out.print("Số lượng: ");
		int quantity = Integer.parseInt(sc.nextLine());

		System.out.print("Giá mỗi chiếc: ");
		float unitPrice = Integer.parseInt(sc.nextLine());

		// Solution: totalAmount
		float totalAmount = quantity * unitPrice;

		//Output: productName, quantity, unitPrice, totalAmount
		System.out.println("---- HÓA ĐƠN ----");
		System.out.println("Sản phẩm: " + productName);
		System.out.println("Số lượng: " + quantity);
		System.out.printf("Đơn giá: %,.0f\n", unitPrice);
		System.out.printf("Thành tiền: %,.0f", totalAmount);
	}
}
