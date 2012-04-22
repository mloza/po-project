package karta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import leczenie.*;
import java.util.ArrayList;

public class Wizyta implements Serializable {
	private GregorianCalendar dateOfVisit;
	Map diagnoza = new HashMap<String, String>();
	ArrayList<Badanie> jakieBadania = new ArrayList<Badanie>();

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
}
