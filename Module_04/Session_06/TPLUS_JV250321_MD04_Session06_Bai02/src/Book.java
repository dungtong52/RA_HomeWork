public class Book {
	private String title;
	private String author;
	private double price;

	public Book () {
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public String getAuthor () {
		return author;
	}

	public void setAuthor (String author) {
		this.author = author;
	}

	public double getPrice () {
		return price;
	}

	public void setPrice (double price) {
		if (price > 0) {
			this.price = price;
		} else
			System.out.println("Giá không được âm");
	}
}
