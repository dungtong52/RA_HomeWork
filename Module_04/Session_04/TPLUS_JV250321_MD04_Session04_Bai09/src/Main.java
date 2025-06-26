import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào URL: ");
		String url = sc.nextLine();

		String urlRegex = "^https?://[^\\s]+";
		if (Pattern.matches(urlRegex, url)) {
			String protocol = "";
			String domain = "";
			String path = "";

			// PROTOCOL
			String noProtocol = url;
			if(url.contains("https")){
				protocol += "https";
				noProtocol = url.substring(8);
			} else {
				protocol += "http";
				noProtocol = url.substring(7);
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
