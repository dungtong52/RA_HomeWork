public class BankAcount implements IBank {
	private String accountId;
	private double balance;
	private String userName;
	private String phoneNumber;

	public BankAcount (String accountId, double balance, String userName, String phoneNumber) {
		this.accountId = accountId;
		this.balance = balance;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
	}

	public String getAccountId () {
		return accountId;
	}

	public void setAccountId (String accountId) {
		this.accountId = accountId;
	}

	public double getBalance () {
		return balance;
	}

	public void setBalance (double balance) {
		this.balance = balance;
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName (String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void deposit (double amount) {
		this.balance += amount;
		System.out.printf("Đã nhận tiền: %,.1f\n", amount);
	}

	@Override
	public void withdraw (double amount) {
		this.balance -= amount;
		System.out.printf("Đã rút tiền: %,.1f\n", amount);
	}

	public void displayBalance () {
		System.out.printf("Số dư tài khoản (%s): %,.1f\n", accountId, balance);
	}
}
