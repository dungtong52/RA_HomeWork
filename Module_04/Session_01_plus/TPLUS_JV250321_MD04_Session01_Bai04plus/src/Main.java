public class Main {
	public static void main (String[] args) {
		// Input
		String[] nameArr = {"Nguyen Van A", "Nguyen Van B", "Nguyen Van C", "Nguyen Van D", "Nguyen Van E", "Nguyen Van F", "Nguyen Van G", "Nguyen Van H", "Nguyen Van I", "Nguyen Van K"};
		int[] ageArr = {20, 21, 18, 19, 22, 21, 23, 19, 18, 21};
		String[] phoneNumberArr = {"0904488481", "0904488482", "0904488483", "0904488484", "0904488485", "0904488486", "0904488487", "0904488488", "0904488489", "0904488480"};
		String[] emailArr = {"anv@rikkeiacademy.com", "bnv@rikkeiacademy.com", "cnv@rikkeiacademy.com", "dnv@rikkeiacademy.com", "env@rikkeiacademy.com", "fnv@rikkeiacademy.com", "gnv@rikkeiacademy.com", "hnv@rikkeiacademy.com", "inv@rikkeiacademy.com", "knv@rikkeiacademy.com"};

		// Output
		System.out.println("                                     DANH SACH SINH VIEN                                        ");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("| STT |  Ho va ten         |  Tuoi   |  So dien thoai  |  Email                              |");
		System.out.println("----------------------------------------------------------------------------------------------");
		for (int i = 0; i < 10; i++){
			if (i < 9)
				System.out.printf("| %d   |  %s      |   %d    |  %s     |  %s              |\n", i+1, nameArr[i], ageArr[i], phoneNumberArr[i], emailArr[i]);
			else
				System.out.printf("| %d  |  %s      |   %d    |  %s     |  %s              |\n", i+1, nameArr[i], ageArr[i], phoneNumberArr[i], emailArr[i]);
			System.out.println("----------------------------------------------------------------------------------------------");
		}
	}
}
