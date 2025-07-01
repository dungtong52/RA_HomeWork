public class Main {
	public static void main (String[] args) {

	}

	public static void generateReport (String name, int age, double[] scores) {
		double averageScores = calculateAverageScores(scores);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Average Score: " + averageScores);
		System.out.println("Grade: " + performance(averageScores));
	}

	public static double calculateAverageScores (double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum / array.length;
	}

	public static String performance (double score) {
		if (score >= 8) {
			return "Exellent";
		} else if (score >= 5) {
			return "Good";
		} else
			return "Fail";
	}

}
