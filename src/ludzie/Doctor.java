package ludzie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Doctor extends Worker{
	public Doctor(Occupation type, String name, String surname) {
		super(type, name, surname);
	}

	public void createAcc(Occupation type, String name, String surname) throws IOException{
		if (type == Occupation.ADMINISTRATOR) {
			Doctor nowy = new Doctor(type, name, surname);
			String fileName = "workers/a_"+surname+"_"+name+".txt";
			FileWriter save = new FileWriter(fileName);
			save.write(nowy.toString());
			save.close();
		}
		/*
		 * if(type == Occupation.DOCTOR){
		 * 
		 * } if(type == Occupation.NURSE){
		 * 
		 * } if(type == Occupation.SUPERCIEC){
		 */
/*
		else {
			System.out.println("Niewlasciwy typ konta");
			return null;
		}
*/
	}


	public void deleteAcc(String name, String surname) {
		String del = "workers/a_"+surname+"_"+name+".txt";
			File file = new File(del);
			System.out.println(file.delete());
		
	}

}
