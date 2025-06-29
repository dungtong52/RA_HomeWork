public class Main {
	public static void main (String[] args) {
		Fraction fraction1 = new Fraction(1, 2);
		Fraction fraction2 = new Fraction(3, 4);

		// Phép cộng
		Fraction resultAdd = fraction1.add(fraction2);
		System.out.println("f1 + f2 = " + resultAdd.toString());

		// Phép trừ
		Fraction resultSub = fraction1.subtract(fraction2);
		System.out.println("f1 - f2 = " + resultSub.toString());

		// Phép nhân
		Fraction resultMul = fraction1.multiply(fraction2);
		System.out.println("f1 * f2 = " + resultMul.toString());

		// Phép chia
		Fraction resultDiv = fraction1.divide(fraction2);
		System.out.println("f1 / f2 = " + resultDiv.toString());
	}
}
