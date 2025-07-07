public class Student {
	private int id;
	private String name;
	private double avgScore;

	public Student () {
	}

	public Student (int id, String name, double avgScore) {
		this.id = id;
		this.name = name;
		this.avgScore = avgScore;
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

	public double getAvgScore () {
		return avgScore;
	}

	public void setAvgScore (double avgScore) {
		this.avgScore = avgScore;
	}

	public String classifyStudent () {
		if (avgScore >= 8.0) {
			return "Giỏi";
		} else if (avgScore >= 6.5) {
			return "Khá";
		} else if (avgScore >= 5.0) {
			return "Trung bình";
		} else {
			return "Yếu";
		}
	}

	public String displayData () {
		return String.format("ID: %d - Name: %s - Average Score: %.1f", this.id, this.name, this.avgScore);
	}
}
