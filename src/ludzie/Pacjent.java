package ludzie;
import java.io.IOException;
import java.nio.CharBuffer;

import files.*;


public class Pacjent extends Person implements Writable,Readable {

	Pacjent(String name, String surname) {
		super(name, surname);
		
	}

	


	
	public String[] fieldsToSave() {
		return null;
	}

	
	public String getClassToSave() {
		return null;
	}



	public int read(CharBuffer cb) throws IOException {
		return 0;
	}

}
