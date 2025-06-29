public class Fraction {
	private int numerator;
	private int denominator;

	// Constructor
	public Fraction (int numerator, int denominator) {
		if (denominator == 0) {
			System.err.println("Mẫu số phải khác 0"); // Chưa học ném ngoại lệ nên ném tạm cái này
			denominator = 1;
		}
		// Đưa sấu âm lên tử số
		if (denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	// Getter & Setter
	public int getNumerator () {
		return numerator;
	}

	public void setNumerator (int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator () {
		return denominator;
	}

	public void setDenominator (int denominator) {
		this.denominator = denominator;
	}

	// Method
	// Cộng phân số
	public Fraction add (Fraction fraction) {
		int addNumerator = fraction.getNumerator() * this.denominator + fraction.getDenominator() * this.numerator;
		int addDenominator = fraction.getDenominator() * this.denominator;
		Fraction newFraction = new Fraction(addNumerator, addDenominator);
		return newFraction.simplify();
	}

	// Trừ phân số (hiện tại trừ tham số)
	public Fraction subtract (Fraction fraction) {
		int subNumerator = this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator;
		int subDenominator = fraction.getDenominator() * this.denominator;
		Fraction newFraction = new Fraction(subNumerator, subDenominator);
		return newFraction.simplify();
	}

	// Nhân phân số
	public Fraction multiply (Fraction fraction) {
		int mulNumerator = fraction.getNumerator() * this.numerator;
		int mulDenominator = fraction.getDenominator() * this.denominator;
		Fraction newFraction = new Fraction(mulNumerator, mulDenominator);
		return newFraction.simplify();
	}

	// Chia phân số (hiện tại chia tham số)
	public Fraction divide (Fraction fraction) {
		int divNumerator = this.numerator * fraction.getDenominator();
		int divDenominator = this.denominator * fraction.getNumerator();
		Fraction newFraction = new Fraction(divNumerator, divDenominator);
		return newFraction.simplify();
	}

	// Tìm UCLN
	public int gcd (int numerator, int denominator) {
		while (denominator != 0) {
			int temp = denominator;
			denominator = numerator % denominator;
			numerator = temp;
		}
		return numerator;
	}

	// Đơn giản hóa phân số
	public Fraction simplify () {
		int UCLN = gcd(numerator, denominator);
		int newNumerator = numerator / UCLN;
		int newDenominator = denominator / UCLN;
		return new Fraction(newNumerator, newDenominator);
	}

	// In phân số dạng tử số/mẫu số
	@Override
	public String toString () {
		return numerator + "/" + denominator;
	}
}
