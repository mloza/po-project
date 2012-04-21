package karta;

import java.io.Serializable;
import java.util.GregorianCalendar;

import choroby.Choroba;

public class Wpis implements Serializable {
	 
	GregorianCalendar dateOfEntry;
	private Choroba whatDisease;
	
	Wpis(GregorianCalendar dateOfEntry, Choroba whatDisease){
		
		this.dateOfEntry = dateOfEntry;
		this.setWhatDisease(whatDisease);
		
	}
	Wpis(int year, int month, int day, Choroba whatDisease){
		this.dateOfEntry = new GregorianCalendar(year, month, day);
		this.setWhatDisease(whatDisease);
	}
	public Choroba getWhatDisease() {
		return whatDisease;
	}
	public void setWhatDisease(Choroba whatDisease) {
		this.whatDisease = whatDisease;
	}
	public String toString(){
		String output = new String();
		output = dateOfEntry.toString();
		output += whatDisease.toString();
		return output;
	}
	
}