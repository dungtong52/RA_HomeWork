public class Main {
	public static void main (String[] args) {
		double[] marks = {7.5, 8.0, 6.5};
		double averageScores = calculateAverageScores(marks);

		System.out.println(averageScores >= 5 ? "Pass" : "Fail");
	}

	public static double calculateAverageScores (double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum / array.length;
	}
}
