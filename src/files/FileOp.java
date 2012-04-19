package files;

import java.io.*;
import java.lang.reflect.Field;

public class FileOp {
	private Writable object;
	private String sep = "$|$";
	private String sep2 = "$$";
	private String encoding = "none"; 
	private StringBuilder toSave = new StringBuilder();
	
	private FileOp(Writable object) { 
		this.object = object;
		//dodajemy na początek pliku typ szyfrowania i separator pól
		toSave.append(sep + "\n" + encoding + "\n");
	}
	
	public static boolean save(Writable object)
	{
		FileOp file = new FileOp(object);
		return file.saveObj();
	}
	
	protected boolean saveObj()
	{
		String[] fields = object.fieldsToSave();
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
		
		try {
			DataInputStream file = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
			System.out.println(file.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(toSave);
		return true;
	}
}
