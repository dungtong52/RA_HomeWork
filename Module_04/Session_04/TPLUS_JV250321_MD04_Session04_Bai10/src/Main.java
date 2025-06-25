import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào chuỗi: ");
        String originStr = sc.nextLine();
        String newStr = originStr;

//		Xóa ký tự đặc biệt:
//		Chỉ giữ lại các ký tự chữ cái (a-z, A-Z), số (0-9), và khoảng trắng.
//		Sử dụng biểu thức chính quy để xác định các ký tự cần xóa.

        newStr = originStr.replaceAll("[^a-zA-Z0-9\\s]", "");
        System.out.println(newStr);

//		Chuẩn hóa văn bản:
//		Chuyển toàn bộ văn bản về dạng chữ thường (toLowerCase()).
//				Xóa các khoảng trắng dư thừa bằng cách:
//		Sử dụng trim() để loại bỏ khoảng trắng ở đầu và cuối.
//				Sử dụng Regex để thay thế nhiều khoảng trắng liên tiếp thành một khoảng trắng duy nhất.

        newStr = newStr.toLowerCase().trim();
        newStr = newStr.replaceAll("[\\s]+", " ");
        System.out.println(newStr);

//		Trích xuất các số:
//		Sử dụng biểu thức chính quy để tìm tất cả các số xuất hiện trong chuỗi.
//		In các số dưới dạng một danh sách.

        StringBuilder numberString = new StringBuilder("");

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(originStr);

        while (matcher.find()) {
            numberString.append(matcher.group());
        }

        System.out.println(numberString);
    }
}
