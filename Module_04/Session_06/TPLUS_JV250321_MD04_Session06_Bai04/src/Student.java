public class Student {
	private String name;
	private int age;
	private String className;
	private double avgScore;

	public Student (String name, int age, String className, double avgScore) {
		this.name = name;
		this.age = age;
		this.className = className;
		this.avgScore = avgScore;
	}

	public double getAvgScore () {
		return avgScore;
	}

	public void display () {
		System.out.printf("Tên: %s, Tuổi: %d, Lớp: %s, Điểm trung bình: %.1f\n", this.name, this.age, this.className, this.avgScore);
	}
}
