import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào chuỗi: ");
        String originStr = sc.nextLine();

        if (originStr.isBlank())
            System.err.println("Không có văn bản để xử lý.");
        else {

            // Xóa ký tự đặc biệt
            String strWithoutSpaceTwoSide = originStr.replaceAll("[^a-zA-Z0-9\\s]", "");
            System.out.println("chuỗi sau khi bỏ ký tự đặc biệt: " + strWithoutSpaceTwoSide);

            // Xóa khoảng trắng liên tiếp
            String strWithoutSpace = strWithoutSpaceTwoSide.toLowerCase().trim().replaceAll("[\\s]+", " ");
            System.out.println("chuỗi sau khi bỏ nhiều khoảng trắng liên tiếp: " + strWithoutSpace);

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

            String resultStr = strWithoutSpace.replaceAll("[0-9]+", "");
            System.out.println("chuỗi sau khi bỏ số: " + resultStr.trim());

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
