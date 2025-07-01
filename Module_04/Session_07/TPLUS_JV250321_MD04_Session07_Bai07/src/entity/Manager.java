package entity;

public class Manager extends Employee {
	private double bonus;

	public Manager () {
	}

	public Manager (String name, String id, double salary, double bonus) {
		super(name, id, salary);
		this.bonus = bonus;
	}

	public double getBonus () {
		return bonus;
	}

	public void setBonus (double bonus) {
		this.bonus = bonus;
	}

	@Override
	public double getSalary () {
		return super.getSalary() + this.getBonus();
	}

	@Override
	public String toString () {
		return String.format("Name: %8s, ID: %5s, Base salary: %,8.1f, Bonus: %,8.1f",
				super.getName(), super.getId(), super.getSalary(), this.getBonus());
	}
}
