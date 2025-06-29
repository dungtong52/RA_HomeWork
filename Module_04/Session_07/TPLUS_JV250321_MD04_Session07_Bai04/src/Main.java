public class Main {
	public static void main (String[] args) {
		BankAcount accountA = new BankAcount("A001", 0, "Nguyễn Văn A", "0123456789");
		BankAcount accountB = new BankAcount("B001", 0, "Nguyễn Thị B", "0987654321");

		// Nạp tiền vào tài khoản A
		accountA.deposit(1000);

		// Chuyển tiền từ A đến B
		double transferAmount = 300;
		accountA.withdraw(transferAmount);
		accountB.deposit(transferAmount);

		// Hiển thị số dư tài khoản A và B
		accountA.displayBalance();
		accountB.displayBalance();
	}

}
