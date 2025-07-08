import java.time.LocalDate;

public class Task {
	private int id;
	private String title;
	private User assignedTo;
	private LocalDate dueDate;
	boolean completed;

	public Task () {
	}

	public Task (int id, String title, User assignedTo, LocalDate dueDate, boolean completed) {
		this.id = id;
		this.title = title;
		this.assignedTo = assignedTo;
		this.dueDate = dueDate;
		this.completed = completed;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public User getAssignedTo () {
		return assignedTo;
	}

	public void setAssignedTo (User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public LocalDate getDueDate () {
		return dueDate;
	}

	public void setDueDate (LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted () {
		return completed;
	}

	public void setCompleted (boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString () {
		String assignedName = assignedTo.getName();

		return "- Title: " + this.title + " - Assigned To: " + assignedName + " - Dateline: " + this.dueDate;
	}
}
