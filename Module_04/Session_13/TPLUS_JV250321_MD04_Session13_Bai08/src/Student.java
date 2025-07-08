public class Student {
	private String name;
	private String major;
	private double score;

	public Student () {
	}

	public Student (String name, String major, double score) {
		this.name = name;
		this.major = major;
		this.score = score;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getMajor () {
		return major;
	}

	public void setMajor (String major) {
		this.major = major;
	}

	public double getScore () {
		return score;
	}

	public void setScore (double score) {
		this.score = score;
	}

	@Override
	public String toString () {
		return String.format("* Name: %s - Major: %s - Score: %.1f", this.name, this.major, this.score);
	}
}
