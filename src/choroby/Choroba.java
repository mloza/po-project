package choroby;

import files.*;

public class Choroba implements Writable {
	private String nazwa = "asd";
	private String opis = "asd";
	public String[] objawy;
	private String[] fieldsToSave = {"nazwa", "opis"};
	
	public String[] fieldsToSave()
	{
		return this.fieldsToSave ;
	}
	
	public static void main(String[] args)
	{
		System.out.println("start");
		
		FileOp.save(new Choroba());
	}
}
