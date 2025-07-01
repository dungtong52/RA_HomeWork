public class Main {
	public static void main (String[] args) {
		int number1 = 5;
		int number2 = 10;
		int result = calculateProductAndSum(number1, number2);
		System.out.println("Result: " + result);
	}

	public static int calculateProductAndSum (int num1, int num2){
		return num1 * num2 + (num1 + num2);
	}
}
