package karta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leczenie.Badanie;
import leczenie.BadanieNotFoundException;
import leczenie.Lek;
import leczenie.LekNotFoundException;
import choroby.Choroba;
import choroby.Objaw;
import choroby.ObjawNotFoundException;
import choroby.SicknessNotFoundException;

public class Wizyta implements Serializable {
	private GregorianCalendar dateOfVisit;
	Map<Choroba, String> diagnoza = new HashMap<Choroba, String>();
	Map<Badanie, String> jakieBadania = new HashMap<Badanie, String>();
	Set<Objaw> objawy = new HashSet<Objaw>();
	Map<Choroba, List<Lek>> leki = new HashMap<Choroba, List<Lek>>();
	String leczenie;
	private boolean odbyta = false;
	private boolean potwierdzona = false;
	
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
		
		try {
			chh = Choroba.find("Martwy");
			String opis = "Brak fal mózgowych wskazuje jednoznacznie że pacjent opuścił już ten świat.";
			diagnoza.put(chh, opis);
		} catch (SicknessNotFoundException e) {
			System.out.println("Przeziębienie jest niezdefiniowane");
			//e.printStackTrace();
		}
		
		leczenie = "Pochować jak najszybciej. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus fermentum, libero et euismod varius, urna enim lobortis enim, vel volutpat est mauris quis arcu. Vivamus dignissim gravida rhoncus. Sed vel augue sed magna lacinia lacinia nec eget ante. Quisque tincidunt felis eu nulla egestas gravida. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean euismod, metus venenatis congue aliquet, purus libero vestibulum diam, vitae pretium lacus ligula eget justo. Sed tempus tempus velit, vel sollicitudin magna vulputate a. Vivamus massa sem, pulvinar in posuere quis, dapibus ut urna. Curabitur suscipit adipiscing laoreet. Proin sit amet eros eu tellus ultrices consequat. Mauris id erat vel augue vulputate viverra. Sed porta vehicula dolor, eget imperdiet eros aliquet at. Nulla facilisi. Aenean facilisis mi et metus porta ut imperdiet velit ultrices. Proin sodales iaculis metus, id vehicula erat pulvinar ac.";
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
		
		try {
			jakieBadania.put(Badanie.find("Elektroencefalogram"), "Badanie wykazało że pacjent nie posiada fal mózgowych.");
		} catch (BadanieNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			List<Lek> list = new ArrayList<Lek>();
			Lek as = Lek.find("Panadol");
			if(as != null)
				list.add(as);
			as = Lek.find("Apap");
			if(as != null)
				list.add(as);
			leki.put(Choroba.find("Grypa"), list);
		} catch (LekNotFoundException e) {
			e.printStackTrace();
		} catch (SicknessNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			List<Lek> list = new ArrayList<Lek>();
			Lek as = Lek.find("Scroll of resurection");
			if(as != null)
				list.add(as);
			System.out.println(as.getNazwa());
			as = Lek.find("Lek 1");
			if(as != null)
				list.add(as);
			System.out.println(as.getNazwa());
			leki.put(Choroba.find("Przeziębienie"), list);
		} catch (LekNotFoundException e) {
			e.printStackTrace();
		} catch (SicknessNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public String getBadaniaString() {
		StringBuilder ret = new StringBuilder();
		System.out.println("asd");
		for(Map.Entry<Badanie, String> entry: jakieBadania.entrySet())
		{
			System.out.println(entry.getKey().getNazwa());
			if(ret.length() != 0) ret.append(", ");
			ret.append(entry.getKey().getNazwa());
		}
		return ret.toString();
	}

	public String getLekiString() {
		
		StringBuilder ret = new StringBuilder();
		for(Map.Entry<Choroba, List<Lek>> entry: leki.entrySet())
		{
			System.out.println("af:" + entry.getValue().size());
			for(Lek j: entry.getValue())
			{
				System.out.println(j);
				if(ret.length() != 0) ret.append(", ");
				ret.append(j.getNazwa());
			}
		}
		return ret.toString();
	}

	public boolean isOdbyta() {
		return odbyta;
	}

	public void setOdbyta(boolean odbyta) {
		this.odbyta = odbyta;
	}

	public boolean isPotwierdzona() {
		return potwierdzona;
	}

	public void setPotwierdzona(boolean potwierdzona) {
		this.potwierdzona = potwierdzona;
	}
	
}
