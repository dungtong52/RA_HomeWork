package presentation;

import entity.Clothing;
import entity.Electronics;
import entity.Food;
import entity.Product;

public class Main {
	public static void main (String[] args) {
		Product product = new Product("Sách", 50000);
		Electronics laptop = new Electronics("Laptop", 20000000.0);
		Clothing tShirt = new Clothing("Áo thun", 150000.0);
		Food apple = new Food("Táo", 30000.0);

		// Sản phẩm chung
		System.out.println("Sản phẩm: " + product.getName());
		System.out.printf("Giá trước chiết khấu: %,.1f\n", product.getPrice());
		System.out.printf("Giá sau chiết khấu (mặc định): %,.1f\n", product.getFinalPrice());
		System.out.printf("Giá sau chiết khấu (bán 50): %,.1f\n", product.getFinalPrice(50));
		System.out.printf("Giá sau chiết khấu (bán 150): %,.1f\n", product.getFinalPrice(150));
		System.out.println("--------------------");

		// Electronics
		System.out.println("Sản phẩm: " + laptop.getName());
		System.out.printf("Giá trước chiết khấu: %,.1f\n", laptop.getPrice());
		System.out.printf("Giá sau chiết khấu (mặc định): %,.1f\n", laptop.getFinalPrice());
		System.out.printf("Giá sau chiết khấu (bán 50): %,.1f\n", laptop.getFinalPrice(50));
		System.out.printf("Giá sau chiết khấu (bán 150): %,.1f\n", laptop.getFinalPrice(150));
		System.out.println("--------------------");

		// Clothing
		System.out.println("Sản phẩm: " + tShirt.getName());
		System.out.printf("Giá trước chiết khấu: %,.1f\n", tShirt.getPrice());
		System.out.printf("Giá sau chiết khấu (mặc định): %,.1f\n", tShirt.getFinalPrice());
		System.out.printf("Giá sau chiết khấu (bán 50): %,.1f\n", tShirt.getFinalPrice(50));
		System.out.printf("Giá sau chiết khấu (bán 150): %,.1f\n", tShirt.getFinalPrice(150));
		System.out.println("--------------------");

		// Food
		System.out.println("Sản phẩm: " + apple.getName());
		System.out.printf("Giá trước chiết khấu: %,.1f\n", apple.getPrice());
		System.out.printf("Giá sau chiết khấu (mặc định): %,.1f\n", apple.getFinalPrice());
		System.out.printf("Giá sau chiết khấu (bán 50): %,.1f\n", apple.getFinalPrice(50));
		System.out.printf("Giá sau chiết khấu (bán 150): %,.1f\n", apple.getFinalPrice(150));
		System.out.println("--------------------");

	}
}
