import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);

		Person person1 = new Person();
		Person person2 = new Person();

		System.out.print("Nhập tên người thứ 1: ");
		person1.setName(sc.nextLine());
		System.out.print("Nhập tuổi người thứ 1: ");
		person1.setAge(Integer.parseInt(sc.nextLine()));

		System.out.print("Nhập tên người thứ 2: ");
		person2.setName(sc.nextLine());
		System.out.print("Nhập tuổi người thứ 2: ");
		person2.setAge(Integer.parseInt(sc.nextLine()));

		String namePerson1 = person1.getName();
		int agePerson1 = person1.getAge();
		String namePerson2 = person2.getName();
		int agePerson2 = person2.getAge();

		if (agePerson1 > agePerson2){
			System.out.printf("%s lớn tuổi hơn %s", namePerson1, namePerson2);
		} else if (agePerson1 < agePerson2) {
			System.out.printf("%s lớn tuổi hơn %s", namePerson2, namePerson1);
		} else {
			System.out.printf("%s và %s bằng tuổi nhau", namePerson1, namePerson2);
		}
	}
}
