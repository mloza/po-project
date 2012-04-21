package choroby;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import files.*;

public class Choroba implements Externalizable {
	private String nazwa = "asd";
	private String opis = "asd";
	protected List<Objaw> Objawy = new ArrayList<Objaw>();
	private String[] fieldsToSave = {"nazwa", "opis"};
	
	public Choroba()
	{
		Objawy.add(new Objaw());
	}
	
	public String[] fieldsToSave()
	{
		return this.fieldsToSave ;
	}
	
	public String getClassToSave()
	{
		return "choroby.Choroba";
	}
	
	public void setValues()
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
		
		//FileOp2.write(new Choroba(), "choroba.txt", "none");
		try {
			ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("data/choroba.txt"));
			op.writeObject(new Choroba());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Choroba testowa = new Choroba();
		System.out.println("Przed ustawienim");
		testowa.printValues();
		testowa.setValues();
		System.out.println("Po ustawienum");
		testowa.printValues();
		try {
			ObjectInputStream op = new ObjectInputStream(new FileInputStream("data/choroba.txt"));
			testowa = (Choroba)op.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Po odczycie");
		testowa.printValues();
		
		List<Choroba> lista = new LinkedList<Choroba>();
		lista.add(new Choroba());
		Choroba ch = new Choroba();
		ch.setValues();
		lista.add(ch);
		//FileOp2.write(lista, "choroby.txt");
		
		try {
			ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("data/choroby.txt"));
			op.writeObject(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<Choroba> choroby = null;
		try {
			ObjectInputStream op = new ObjectInputStream(new FileInputStream("data/choroby.txt"));
			choroby = (LinkedList<Choroba>)op.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//FileOp2.read(choroby, "choroby.txt");
		for(Choroba f : choroby)
		{
			f.printValues();
		}
		
	}

	public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(Objawy);
			out.writeObject(nazwa);
			out.writeObject(opis);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
			Objawy = (ArrayList<Objaw>)in.readObject();
			nazwa = (String)in.readObject();
			opis = (String)in.readObject();
	}
}
