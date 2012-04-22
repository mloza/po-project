package ludzie;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class Person implements Serializable {
	public String name;
	public String surname;
	private Date birthDate;
	
	Person(String name, String surname, Date birdthDate) {
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birdthDate);
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
