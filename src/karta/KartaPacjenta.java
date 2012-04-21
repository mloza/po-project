package karta;
import java.util.ArrayList;
import files.*;


public class KartaPacjenta implements Writable, Redable {
ArrayList<Wpis> listaWpisow = new ArrayList<Wpis>();
ArrayList<Wizyta> listaWizyt = new ArrayList<Wizyta>();
private String[] fieldsToSave = {};

public String[] fieldsToSave() {
	
	return this.fieldsToSave;
}
public String getClassToSave(){
	return "karta.KartaPacjenta";
}
public void printWpis(int i){
	
	
}

}
