public class Main {
	public static void main (String[] args) {
		int repeatTimes = 100000;
		String joinText = "World";

		// String
		long startTime = System.currentTimeMillis();
		String str = "Hello";
		for (int i = 0; i < repeatTimes; i++) {
			str += joinText;
		}
		long endTime = System.currentTimeMillis();
		System.out.printf("Thời gian thực hiện với String: %d ms\n", (endTime - startTime));

		// StringBuilder
		startTime = System.currentTimeMillis();
		StringBuilder strBuilder = new StringBuilder("Hello");
		for (int i = 0; i < repeatTimes; i++) {
			strBuilder.append(joinText);
		}
		endTime = System.currentTimeMillis();
		System.out.printf("Thời gian thực hiện với StringBuilder: %d ms\n", (endTime - startTime));

		// StringBuffer
		startTime = System.currentTimeMillis();
		StringBuffer strBuffer = new StringBuffer("Hello");
		for (int i = 0; i < repeatTimes; i++) {
			strBuffer.append(joinText);
		}
		endTime = System.currentTimeMillis();
		System.out.printf("Thời gian thực hiện với StringBuffer: %d ms\n", (endTime - startTime));

		// Nhận xét
		System.out.println("- String: Không hiệu quả cho phép nối chuỗi nhiều lần do tạo ra nhiều đối tượng mới");
		System.out.println("- StringBuilder: Hiệu quả và nhanh chóng, thích hợp cho nhiều thao tác nối chuỗi trong 1 luồng");
		System.out.println("- StringBuffer: Tương tự như StringBuilder nhưng an toàn với đa luồng, có thể chậm hơn một chút do đồng bộ hóa");
	}
}
