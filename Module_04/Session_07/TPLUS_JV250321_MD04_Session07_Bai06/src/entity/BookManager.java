package entity;

public class BookManager implements IBookManager {
	private Book[] books;
	private int currentIndex;

	public BookManager (int capacity) {
		books = new Book[capacity];
		currentIndex = 0;
	}

	@Override
	public void addBook (Book book) {
		books[currentIndex] = book;
		currentIndex++;
	}

	@Override
	public void removeBook (String isbn) {
		int indexRemove = findBookIndexByIsbn(isbn);
		if (indexRemove == -1) {
			System.err.println("Không tồn tại Book này");
		} else {
			for (int i = indexRemove; i < currentIndex - 1; i++) {
				books[i] = books[i + 1];
			}
			books[currentIndex - 1] = null;
			currentIndex--;
			System.out.printf("Đã xóa sách với ISBN: %s\n", isbn);
		}
	}

	@Override
	public void displayBooks () {
		for (int i = 0; i < currentIndex; i++) {
			System.out.printf("%2d. %5s - %5s\n", i + 1, books[i].getTitle(), books[i].getAuthor());
		}
	}

	public int findBookIndexByIsbn (String isbn) {
		int index = -1;
		for (int i = 0; i < currentIndex; i++) {
			if (books[i].getIsbn().equals(isbn)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
