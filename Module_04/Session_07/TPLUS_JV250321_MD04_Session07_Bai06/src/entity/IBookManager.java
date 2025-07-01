package entity;

public interface IBookManager {
	void addBook(Book book);
	void removeBook(String isbn);
	void displayBooks();
}
