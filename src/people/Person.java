package people;

public abstract class Person{
	private String surname;
	private String name;
	public Person(String surname, String name){
		this.setName(name);
		this.setSurname(surname);
	}
	public String getSurname() {
		return surname;
	}
	private void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
}
