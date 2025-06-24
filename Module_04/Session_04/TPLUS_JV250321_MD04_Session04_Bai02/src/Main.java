
public class Main {
	public static void main (String[] args) {
		StringBuilder sb = new StringBuilder("Hello, Java World!");
		System.out.println("Chuỗi ban đầu: " + sb);
		sb.delete(5, 10);
		System.out.println("Chuỗi sau khi xóa: " + sb);
		sb.replace(7, 12, "Universe");
		System.out.println("Chuỗi sau khi thay thế: " + sb);
	}
}
