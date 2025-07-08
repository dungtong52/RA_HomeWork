import java.util.Optional;

public class User {
	private int id;
	private String name;
	private Optional <String> email;

	public User () {
	}

	public User (int id, String name, Optional <String> email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Optional <String> getEmail () {
		return email;
	}

	public void setEmail (Optional <String> email) {
		this.email = email;
	}

	@Override
	public String toString () {
		String emailString = email.map(String::toString).orElse("chưa có");
		return "- Name: " + this.name + " - Email: " + emailString;
	}
}
