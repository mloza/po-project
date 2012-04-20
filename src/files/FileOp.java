package files;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class FileOp {
	private Writable object;
	private Collection objectCollection;
	private Redable objectR;
	private String sep = "$|$";
	private String sep2 = "$$";
	private String[] fields;
	private String encoding = "none"; 
	private StringBuilder toSave = new StringBuilder();
	
	private FileOp(Writable object) { 
		this.object = object;
		//dodajemy na początek pliku typ szyfrowania i separator pól
		toSave.append(sep + "\n" + encoding + "\n");
	}
	
	private FileOp(Collection object) { 
		this.objectCollection = object;
		//dodajemy na początek pliku typ szyfrowania i separator pól
		toSave.append(sep + "\n" + encoding + "\n");
	}
	
	private FileOp(Redable object)
	{
		this.objectR = object;

	}
	
	public static boolean save(Writable object)
	{
		FileOp file = new FileOp(object);
		file.saveObj(object);
		
		try {
			DataOutputStream fileF = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
			fileF.writeUTF(file.toSave.toString());
			fileF.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean save(Collection object)
	{
		FileOp file = new FileOp(object);
		file.saveCollection(object);
		
		try {
			DataOutputStream fileF = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
			fileF.writeUTF(file.toSave.toString());
			fileF.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean read(Redable object)
	{
		FileOp file = new FileOp(object);
		file.readObj();
		return true;
	}
	
	protected boolean saveCollection(Collection object)
	{
		toSave.append("[\n");
		for(Object i: object)
		{
			if(!(i instanceof Writable)) return false;
			this.saveObj((Writable) i);
		}
		toSave.append("]\n");
		return true;
	}
	
	protected boolean saveObj(Writable object)
	{
		this.fields = object.fieldsToSave();
		StringBuilder line = new StringBuilder();
		//budowanie nagłówka pliku
		for(String f: fields)
		{
			if(line.length() != 0) line.append(sep);
			else line.append("{\n");
			line.append(f);
		}
		line.append("\n");
		toSave.append(line);
		// budowanie wartości pól
		line = new StringBuilder();
		Class<?> objectC = object.getClass();
		for(String f: fields)
		{
			if(line.length() != 0) line.append(sep);
			try {
				Field ps = objectC.getDeclaredField(f);
				ps.setAccessible(true);
				line.append(ps.get(object));
			}
			catch(Exception e)
			{
				line.append("*");
			}
		}
		// koniec obiektu
		line.append("\n}\n");
		toSave.append(line);	
		//System.out.println(toSave);
		return true;
	}
	
	protected boolean readObj()
	{
		String values[];
		try {
			DataInputStream file = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
			values = file.readUTF().split("\n");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		this.sep = values[0];
		this.encoding = values[1];
		
		if(values[2].contentEquals("{") && values[values.length-1].contentEquals("}"))
		{
			if(this.readfields(objectR, 3, values.length-1, values))
			{
				System.out.println("Poprawny odczyt");
				return true;
			} else {
				System.out.println("Nie można dokonać odczytu");
				return false;
			}
		} else {
			System.out.println("Read error" + values[values.length-1]);
		}
		
		return true;
	}
	
	protected boolean readfields(Redable object, int start, int end, String[] values)
	{
		if(!this.checkHeader(values[start], object)) return false;
		String[] v = values[start+1].split(Pattern.quote(this.sep));
		Class<?> objectC = object.getClass();
		for(int i=0; i<v.length; i++)
		{
			try {
				Field ps = objectC.getDeclaredField(this.fields[i]);
				ps.setAccessible(true);
				ps.set(object, v[i]);
			}
			catch(Exception e)
			{
				System.out.println("Nie udało się ustawić pola: " + this.fields[i]);
			}
		}
		
		return true;
	}
	
	protected boolean checkHeader(String values, Redable object)
	{
		this.fields = object.fieldsToSave();
		String[] splitVals = values.split(Pattern.quote(this.sep));
		List<String> fields = Arrays.asList(this.fields);
		for(String i : splitVals)
		{
			if(fields.indexOf(i) == -1) return false;
		}
		return true;
	}
}
