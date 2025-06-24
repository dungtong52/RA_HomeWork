import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào kích thước ma trận: ");
        int n = Integer.parseInt(sc.nextLine());
        if(n <= 0){
            System.err.println("Ma trận không hợp lệ");
        } else {
            int[][] originArr = new int[n][n];
            // nhập giá trị cho mảng
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    originArr[i][j] = (int)Math.ceil(Math.random() * 10) - 1;
                }
            }
            // Xoay ma trận
            int[][] newArr = new int[n][n];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    newArr[i][n-1-j] = originArr[j][i];
                }
            }
            // In ra ma trận
            System.out.println("Ma trận ban đầu: ");
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    System.out.print(originArr[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("Ma trận sau khi xoay: ");
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    System.out.print(newArr[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }
}
