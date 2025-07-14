package entity;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Name: %s - Age: %d\n",
                this.id, this.name, this.age);
    }

    public void inputData(Scanner scanner) {
        this.name = inputName(scanner);
        this.age = inputAge(scanner);
    }

    public String inputName(Scanner scanner) {
        System.out.print("Enter student's name: ");
        return scanner.nextLine();
    }

    public int inputAge(Scanner scanner) {
        System.out.print("Enter student's age: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
