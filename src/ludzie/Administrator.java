package ludzie;

import java.util.Date;

import run.Login;

public class Administrator extends Person implements worker{
	private String login;
	public Administrator(String login, String name, String surname, Date birthDate){
		super(name, surname, birthDate);
		this.setLogin(login);
		System.out.println("bangla");
		System.out.println(Login.logIn);
	}

	public String toString(){
		return "Administrator "+this.getLogin();
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
