import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào chuỗi: ");
        String originStr = sc.nextLine();

        if (originStr == "") System.err.println("Không có văn bản để xử lý.");
        else {
            String newStr = originStr;

            // Xóa ký tự đặc biệt
            newStr = originStr.replaceAll("[^a-zA-Z0-9\\s]", "");
            System.out.println("chuỗi sau khi bỏ ký tự đặc biệt: " + newStr);

            // Xóa khoảng trắng liên tiếp
            newStr = newStr.toLowerCase().trim();
            newStr = newStr.replaceAll("[\\s]+", " ");
            System.out.println("chuỗi sau khi bỏ nhiều khoảng trắng liên tiếp: " + newStr);

            // Trích xuất số
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(originStr);

            int count = 0;
            while (matcher.find()) {
                count++;
            }
            matcher.reset();

            String[] numberArr = new String[count];
            int index = 0;
            while(matcher.find()){
                numberArr[index] = matcher.group();
                index++;
            }

            newStr = newStr.replaceAll("[0-9]+", "");
            System.out.println("chuỗi sau khi bỏ số: " + newStr.trim());

            // In các số trong array
            for(int i = 0; i < numberArr.length; i++){
                System.out.print(numberArr[i]);
                if(i < numberArr.length - 1){
                    System.out.print(", ");
                }
            }
        }

    }
}
