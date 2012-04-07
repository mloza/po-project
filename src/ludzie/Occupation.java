package ludzie;

import java.io.IOException;

public enum Occupation{
	ADMINISTRATOR("Administrator"), DOCTOR("Doktor"), NURSE("Pielegniarka"), SUPERCIEC("Superciec");
	String nazwa;
	Occupation(String nazwa){
		this.nazwa = nazwa;
	}
	public String getNazwa(){
		return nazwa;
	}
}


/*
interface account{
	int id;
	String password;
}
*/

interface admin{
	public void createAcc(Occupation type, String imie, String nazwisko) throws IOException;
	public void deleteAcc(String name, String surname); 
}

