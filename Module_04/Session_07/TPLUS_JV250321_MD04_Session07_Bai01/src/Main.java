public class Main {
	public static void main (String[] args) {
		Animals[] animalList = new Animals[5];

		animalList[0] = new Dog("Buddy", 3, "Belarus");
		animalList[1] = new Cat("Kitty", 1, "Tabby");
		animalList[2] = new Dog("Max", 2, "Alaska");
		animalList[3] = new Cat("Luna", 4, "Bicolor");
		animalList[4] = new Dog("Rex", 3, "Pug");

		for (Animals animal : animalList) {
			animal.displayInfo();
			System.out.println("Sound: " + animal.makeSound());
			System.out.println("---------");
		}
	}
}
