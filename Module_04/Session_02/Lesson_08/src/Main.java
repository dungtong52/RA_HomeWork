import java.util.Scanner;

public class Main {
	public static void readNumber(int unit, int ten, int hundred){
		String [] unitArr = {"", "mốt", "hai", "ba", "tư", "lăm", "sáu", "bảy", "tám", "chín"};
		String [] tenArr = {"lẻ", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
		String [] hundredArr = {"", "Một trăm", "Hai trăm", "Ba trăm", "Bốn trăm", "Năm trăm", "Sáu trăm", "Bảy trăm", "Tám trăm", "Chín trăm"};

		String readHundred = hundredArr[hundred];
		String readTen = tenArr[ten];
		String readUnit = unitArr[unit];

		if(unit == 0 && ten == 0) readTen = "";
		if(ten < 2 && unit == 1) readUnit = "một";
		if(ten == 0 && unit == 5) readUnit = "năm";
		System.out.printf("%s %s %s", readHundred, readTen, readUnit);
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;

		while(true){
			System.out.print("Nhập vào 1 số 100-999: ");
			if(sc.hasNextInt()){
				n = sc.nextInt();
				if (n < 100 || n > 999) System.out.println("Số nhập vào không hợp lệ");
				else {
					int units = n % 10;
					int tens = (n / 10) % 10;
					int hundreds = (n / 100);
					readNumber(units, tens, hundreds);
				}
				break;
			} else sc.next();
			System.out.println("Hãy nhập vào 1 số");
		}
		sc.close();
	}
}
