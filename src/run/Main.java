package run;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		AccManager acc = new AccManager();
		acc.start();
		/*
		Administrator admin = new Administrator(Occupation.ADMINISTRATOR, "jakiesimie", "jakiesnazwisko");
		admin.createAcc(Occupation.ADMINISTRATOR, "name", "surname");
		admin.deleteAcc("name", "surname");
		*/
	}

}
