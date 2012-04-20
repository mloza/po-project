package files;

import java.io.*;
import java.lang.reflect.Field;

public class FileOp {
	private Writable object;
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
	
	private FileOp(Redable object)
	{
		this.objectR = object;
		
	}
	
	public static boolean save(Writable object)
	{
		FileOp file = new FileOp(object);
		return file.saveObj();
	}
	
	public static boolean read(Redable object)
	{
		FileOp file = new FileOp(object);
		file.readObj();
		return true;
	}
	
	protected boolean saveObj()
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
		Class<?> objectC = this.object.getClass();
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
		
		try {
			DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
			file.writeUTF(toSave.toString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
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
		
		if(values[2] == "}" && values[values.length] == "}")
		{
			this.readfields(objectR, 3, values.length, values);
		}
		
		return true;
	}
	
	protected boolean readfields(Redable object, int start, int end, String[] values)
	{
		this.checkHeader();
		return true;
	}
}
