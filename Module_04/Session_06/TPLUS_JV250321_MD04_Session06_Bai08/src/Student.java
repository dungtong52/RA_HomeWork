import java.util.Scanner;

public class Student {
	private int id;
	private String name;
	private int age;
	private Gender gender;
	private String address;
	private String phoneNumber;

	public Student () {
	}

	public Student (int id, String name, int age, Gender gender, String address, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getAge () {
		return age;
	}

	public void setAge (int age) {
		this.age = age;
	}

	public Gender getGender () {
		return gender;
	}

	public void setGender (Gender gender) {
		this.gender = gender;
	}

	public String getAddress () {
		return address;
	}

	public void setAddress (String address) {
		this.address = address;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void inputData () {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập mã học sinh: ");
		this.id = Integer.parseInt(scanner.nextLine());

		System.out.print("Nhập tên học sinh: ");
		this.name = scanner.nextLine();

		System.out.print("Nhập tuổi học sinh: ");
		this.age = Integer.parseInt(scanner.nextLine());

		System.out.print("Nhập giới tính học sinh (MALE/FEMALE/OTHER): ");
		String genderInput = scanner.nextLine().toUpperCase();
		this.gender = Gender.valueOf(genderInput);

		System.out.print("Nhập địa chỉ: ");
		this.address = scanner.nextLine();

		System.out.print("Nhập số điện thoại: ");
		this.phoneNumber = scanner.nextLine();
	}

	public void display () {
		System.out.printf("ID: %3d, Name: %8s, Age: %3d, Gender: %8s, Address: %10s, Phone Number: %10s\n"
				, id, name, age, gender, address, phoneNumber);
	}
}
