public class Dog extends Animals {
	private String breed;

	public Dog (String name, int age, String breed) {
		super(name, age);
		this.breed = breed;
	}

	@Override
	public void displayInfo () {
		super.displayInfo();
		System.out.println("Breed: " + breed);
	}

	@Override
	public String makeSound () {
		return "Woof Woof";
	}
}
