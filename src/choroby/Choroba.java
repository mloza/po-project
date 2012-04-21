package choroby;

import java.util.LinkedList;
import java.util.List;

import files.*;

public class Choroba implements Writable, Redable {
	private String nazwa = "asd";
	private String opis = "asd";
	public String[] objawy;
	private String[] fieldsToSave = {"nazwa", "opis"};
	
	public String[] fieldsToSave()
	{
		return this.fieldsToSave ;
	}
	
	public String getClassToSave()
	{
		return "choroby.Choroba";
	}
	
	public void setValue()
	{
		this.nazwa = "ddd";
		this.opis = "fff";
	}
	
	public void printValues()
	{
		System.out.println(this.nazwa);
	}
	
	public static void main(String[] args)
	{
		System.out.println("start");
		
		/*FileOp.save(new Choroba());
		Choroba testowa = new Choroba();
		System.out.println("Przed ustawienim");
		testowa.printValues();
		testowa.setValues();
		System.out.println("Po ustawienum");
		testowa.printValues();
		FileOp.read(testowa);
		System.out.println("Po odczycie");
		testowa.printValues();*/
		
		List<Choroba> lista = new LinkedList<Choroba>();
		lista.add(new Choroba());
		Choroba ch = new Choroba();
		ch.setValues();
		lista.add(ch);
		FileOp.save(lista, "choroby.txt");
		
		List<Choroba> choroby = new LinkedList<Choroba>();
		FileOp.read(choroby, "choroby.txt");
		for(Choroba f : choroby)
		{
			f.printValues();
		}
	}
}
