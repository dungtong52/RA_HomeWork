public class Main {
	public static void main (String[] args) {
		Circle circle = new Circle(5.5);
		Rectangle rectangle = new Rectangle(4, 7);
		Square square = new Square(3);

		circle.setColor("Red");
		rectangle.setColor("Blue");
		square.setColor("Green");

		circle.displayInfo();
		rectangle.displayInfo();
		square.displayInfo();
	}
}
