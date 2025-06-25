public class Main {
	public static void main (String[] args) {
		Rectangle rec1 = new Rectangle();
		System.out.println("Hình chữ nhật 1:");
		rec1.display();

		System.out.println();
		Rectangle rec2 = new Rectangle(5, 3);
		rec2.display();
	}
}
