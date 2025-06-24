import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào URL: ");
		String str = sc.nextLine();


		Pattern pattern = Pattern.compile("^https?://[^/\s]+");
		Matcher matcher = pattern.matcher(str);

		if (matcher.find()) {
			String protocol = "";
			String domain = "";
			String path = "";

			// PROTOCOL
			String noProtocol = str;
			if(str.contains("https")){
				protocol += "https";
				noProtocol = str.substring(8);
			} else {
				protocol += "http";
				noProtocol = str.substring(7);
			}

			// DOMAIN + PATH
			int indexSplash = noProtocol.indexOf("/");
			if(indexSplash == -1){
				domain = noProtocol;
				path = "Không có đường dẫn";
			} else {
				domain = noProtocol.substring(0, indexSplash);
				path = noProtocol.substring(indexSplash);
			}
			System.out.println("Giao thức: " + protocol);
			System.out.println("Tên miền: " + domain);
			System.out.printf("Đường dẫn: " + path);

		} else
			System.out.println("URL không hợp lệ");
	}
}
