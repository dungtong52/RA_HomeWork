public class Main {
	public static void main (String[] args) {
		Book myBook = new Book();

		// Thiết lập thuộc tính
		myBook .setTitle("Java Programing");
		myBook.setAuthor("John Doe");
		myBook.setPrice(29.99);

		// Hiển thị thông tin sách
		System.out.println("Tiêu đề: " + myBook.getTitle());
		System.out.println("Tác giả: " + myBook.getAuthor());
		System.out.println("Giá: " + myBook.getPrice());

		// Thay đổi giá thành công
		myBook.setPrice(35.50);
		System.out.println("Giá mới: " + myBook.getPrice());

		// Thay đổi giá không hợp lệ
		myBook.setPrice(-5.00);
	}
}
