package karta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leczenie.*;
import java.util.ArrayList;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import choroby.Choroba;
import choroby.Objaw;
import choroby.ObjawNotFoundException;
import choroby.SicknessNotFoundException;

public class Wizyta implements Serializable {
	private GregorianCalendar dateOfVisit;
	Map<Choroba, String> diagnoza = new HashMap<Choroba, String>();
	Map<Badanie, String> jakieBadania = new HashMap<Badanie, String>();
	List<Objaw> objawy = new ArrayList<Objaw>();
	Map<Choroba, List<Lek>> leki = new HashMap<Choroba, List<Lek>>();
	String leczenie;

	public Date getDate() {
		return getDateOfVisit().getTime();
	}

	public GregorianCalendar getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(GregorianCalendar dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	
	public String toString()
	{
		final SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		return date.format(this.getDate());
	}

	public void Init() {
		Choroba chh;
		try {
			chh = Choroba.find("Grypa");
			String opis = "Gorączka i osłabienie jednoznacznie wskazują na grypę";
			diagnoza.put(chh, opis);
		} catch (SicknessNotFoundException e) {
			System.out.println("Grypa jest niezdefiniowana");
			//e.printStackTrace();
		}
		
		try {
			chh = Choroba.find("Przeziębienie");
			String opis = "Przeziębienie towarzyszące grypie";
			diagnoza.put(chh, opis);
		} catch (SicknessNotFoundException e) {
			System.out.println("Przeziębienie jest niezdefiniowane");
			//e.printStackTrace();
		}
		leczenie = "Wygrzewanie sie w słońcu, dużo, długo bez przerwy najlepiej. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus fermentum, libero et euismod varius, urna enim lobortis enim, vel volutpat est mauris quis arcu. Vivamus dignissim gravida rhoncus. Sed vel augue sed magna lacinia lacinia nec eget ante. Quisque tincidunt felis eu nulla egestas gravida. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean euismod, metus venenatis congue aliquet, purus libero vestibulum diam, vitae pretium lacus ligula eget justo. Sed tempus tempus velit, vel sollicitudin magna vulputate a. Vivamus massa sem, pulvinar in posuere quis, dapibus ut urna. Curabitur suscipit adipiscing laoreet. Proin sit amet eros eu tellus ultrices consequat. Mauris id erat vel augue vulputate viverra. Sed porta vehicula dolor, eget imperdiet eros aliquet at. Nulla facilisi. Aenean facilisis mi et metus porta ut imperdiet velit ultrices. Proin sodales iaculis metus, id vehicula erat pulvinar ac.";
		try {
			objawy.add(Objaw.find("Gorączka"));
		} catch (ObjawNotFoundException e) {
			System.out.println("Nie ma objawu Gorączka");
		}
		try {
			objawy.add(Objaw.find("Ból głowy"));
		} catch (ObjawNotFoundException e) {
			System.out.println("Nie ma objawu Ból głowy");
		}
	}
	
	public String getDiagnozaString() {
		String ret = "";
		System.out.println("asd");
		for(Map.Entry<Choroba, String> entry: diagnoza.entrySet())
		{
			System.out.println(entry.getKey().getNazwa());
			ret += "Choroba: ";
			ret += entry.getKey().getNazwa();
			ret += "\nDiagnoza: ";
			ret += entry.getValue();
			ret += "\n";
		}
		return ret;
	}
	
	public String getObjawyString()
	{
		StringBuilder str = new StringBuilder();
		for(Objaw i: objawy)
		{
			if(str.length() != 0) str.append(", ");
			str.append(i.getNazwa());
		}
		return str.toString();
	}
}
