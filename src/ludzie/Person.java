package ludzie;

public abstract class Person {
	public String name;
	public String surname;

	Person(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	private void setSurname(String surname) {
		this.surname = surname;
	}

}
