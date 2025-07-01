package entity;

public class Developer extends Employee {
	private String programmingLanguage;

	public Developer () {
	}

	public Developer (String name, String id, double salary, String programmingLanguage) {
		super(name, id, salary);
		this.programmingLanguage = programmingLanguage;
	}

	public String getProgrammingLanguage () {
		return programmingLanguage;
	}

	public void setProgrammingLanguage (String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	@Override
	public double getSalary () {
		return super.getSalary();
	}

	@Override
	public String toString () {
		return String.format("Name: %8s, ID: %5s, Base salary: %,8.1f, Programming Language: %s",
				super.getName(), super.getId(), super.getSalary(), this.getProgrammingLanguage());
	}
}
