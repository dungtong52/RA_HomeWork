public class Square implements Colorable {
	private double edge;
	private String color;

	public Square (double edge) {
		this.edge = edge;
	}

	@Override
	public void setColor (String color) {
		this.color = color;
	}

	public void displayInfo () {
		System.out.println("Square color: " + color);
	}
}
